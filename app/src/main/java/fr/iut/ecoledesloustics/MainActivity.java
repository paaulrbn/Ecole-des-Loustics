package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.iut.ecoledesloustics.db.DatabaseClient;
import fr.iut.ecoledesloustics.db.User;

/**
 * Activité principale de l'application qui gère l'affichage des utilisateurs et l'ajout de nouveaux utilisateurs.
 * Permet également de continuer sans compte ou de sélectionner un utilisateur existant.
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 0;

    // DATA
    private DatabaseClient mDb;
    private UsersAdapter adapter;

    // VIEW
    private ListView listUsers;
    private Button button_continue_without_account, button_add_user;
    private TextView noUser;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues, configure l'adapter, et définit les écouteurs d'événements.
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        listUsers = findViewById(R.id.list_users);
        button_continue_without_account = findViewById(R.id.button_continue_without_account);
        button_add_user = findViewById(R.id.button_add_user);
        noUser = findViewById(R.id.no_user);

        // Lier l'adapter au listView
        adapter = new UsersAdapter(this, new ArrayList<User>());
        listUsers.setAdapter(adapter);

        // ActivityResultLauncher pour le retour de l'ajout d'un utilisateur
        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == RESULT_OK) {
                                    getUsers();
                                }
                            }});

        // Gérer le clic sur le bouton pour continuer sans compte
        button_continue_without_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UTILISATEUR_PRENOM", "");
                editor.apply();
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        // Gérer le clic sur le bouton pour ajouter un utilisateur
        button_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        // Gérer la sélection d'un utilisateur dans la liste
        listUsers.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupération de l'utilisateur cliqué à l'aide de l'adapter
                User user = adapter.getItem(position);

                // Stocker le prénom de l'utilisateur dans SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("UTILISATEUR_ID", user.getId());
                editor.putString("UTILISATEUR_PRENOM", user.getPrenom());
                editor.putInt("UTILISATEUR_SCORE", user.getScore());
                editor.apply();

                // Lancer l'activité de menu principal
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        }));

        // Récupérer et afficher la liste des utilisateurs
        getUsers();
    }

    /**
     * Méthode pour récupérer la liste des utilisateurs dans la base de données de manière asynchrone.
     * Elle met ensuite à jour l'adapter de la ListView et la visibilité des éléments en fonction de la présence d'utilisateurs.
     */
    private void getUsers() {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des utilisateurs et de mettre à jour le listView de l'activité
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                // Récupérer la liste des utilisateurs depuis la base de données
                List<User> userList = mDb.getAppDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> tasks) {
                super.onPostExecute(tasks);

                // Mettre à jour l'adapter avec la liste des utilisateurs
                adapter.clear();
                adapter.addAll(tasks);
                adapter.notifyDataSetChanged();

                // Gérer la visibilité des vues en fonction de la présence d'utilisateurs
                if (tasks.isEmpty()) {
                    listUsers.setVisibility(View.GONE);
                    noUser.setVisibility(View.VISIBLE);
                } else {
                    listUsers.setVisibility(View.VISIBLE);
                    noUser.setVisibility(View.GONE);
                }
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetUsers gt = new GetUsers();
        gt.execute();
    }
}
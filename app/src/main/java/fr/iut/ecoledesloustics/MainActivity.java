package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 0;

    // DATA
    private DatabaseClient mDb;
    private UsersAdapter adapter;

    // VIEW
    private ListView listUsers;
    private Button button_continue_without_account, button_add_user;
    private TextView noUser;


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

        button_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        listUsers.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupération de l'utilisateur cliqué à l'aide de l'adapter
                User user = adapter.getItem(position);

                // Stocker le prénom de l'utilisateur dans SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UTILISATEUR_PRENOM", user.getPrenom());
                editor.apply();

                // Lancer l'activité de menu principal
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        }));

        getUsers();
    }

    private void getUsers() {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = mDb.getAppDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> tasks) {
                super.onPostExecute(tasks);

                // Mettre à jour l'adapter avec la liste de tâches
                adapter.clear();
                adapter.addAll(tasks);
                adapter.notifyDataSetChanged();

                // Gérer la visibilité des vues
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
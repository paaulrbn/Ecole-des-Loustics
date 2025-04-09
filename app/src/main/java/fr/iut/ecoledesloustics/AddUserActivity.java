package fr.iut.ecoledesloustics;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.ecoledesloustics.db.DatabaseClient;
import fr.iut.ecoledesloustics.db.User;

/**
 * Activité permettant d'ajouter un nouvel utilisateur à l'application.
 * Elle récupère les informations saisies par l'utilisateur et les enregistre dans la base de données.
 */
public class AddUserActivity extends AppCompatActivity {

    // Données
    private DatabaseClient mDb;

    // Vues
    private EditText editTextPrenom, editTextNom;
    private Button button_save;
    private TextView retour;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues et définit les écouteurs d'événements.
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextNom = findViewById(R.id.editTextNom);
        button_save = findViewById(R.id.button_save);
        retour = findViewById(R.id.retour);

        // Retour à l'activité précédente
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Sauvegarde des informations de l'utilisateur
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    /**
     * Méthode pour sauvegarder un nouvel utilisateur dans la base de données.
     * Cette méthode vérifie que les champs prénom et nom sont remplis avant d'enregistrer l'utilisateur.
     */
    private void saveUser() {

        final String sPrenom = editTextPrenom.getText().toString().trim();
        final String sNom = editTextNom.getText().toString().trim();

        // Vérification des champs prénom et nom
        if (sPrenom.isEmpty()) {
            editTextPrenom.setError("Prénom requis");
            editTextPrenom.requestFocus();
            return;
        }

        if (sNom.isEmpty()) {
            editTextNom.setError("Nom requis");
            editTextNom.requestFocus();
            return;
        }

        /**
         * Classe asynchrone permettant de sauvegarder l'utilisateur dans la base de données
         */
        class SaveUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {

                // Création d'un nouvel utilisateur
                User user = new User();
                user.setPrenom(sPrenom);
                user.setNom(sNom);
                user.setScore(0);

                // Ajout de l'utilisateur à la base de données
                long id = mDb.getAppDatabase()
                        .userDao()
                        .insert(user);

                // Mise à jour de l'ID de l'utilisateur
                user.setId(id);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                // Lorsque l'utilisateur est ajouté, on ferme l'activité actuelle et on affiche un message de succès
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Utilisateur ajouté", Toast.LENGTH_LONG).show();
            }

        }

        // Exécution de la tâche asynchrone
        SaveUser su = new SaveUser();
        su.execute();
    }
}
package fr.iut.ecoledesloustics;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.iut.ecoledesloustics.db.DatabaseClient;
import fr.iut.ecoledesloustics.db.User;


public class AddUserActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private EditText editTextPrenom, editTextNom;
    private Button button_save;
    private TextView retour;

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

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    private void saveUser() {

        final String sPrenom = editTextPrenom.getText().toString().trim();
        final String sNom = editTextNom.getText().toString().trim();

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
         * Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
         */
        class SaveUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {

                // creating a user
                User user = new User();
                user.setPrenom(sPrenom);
                user.setNom(sNom);

                // adding to database
                long id = mDb.getAppDatabase()
                        .userDao()
                        .insert(user);

                // mettre à jour l'id de l'utilisateur
                // Nécessaire si on souhaite avoir accès à l'id plus tard dans l'activité
                user.setId(id);


                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Utilisateur ajouté", Toast.LENGTH_LONG).show();
            }

        }

        SaveUser su = new SaveUser();
        su.execute();
    }
}
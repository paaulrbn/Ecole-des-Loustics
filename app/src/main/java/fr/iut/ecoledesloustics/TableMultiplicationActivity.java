package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.iut.ecoledesloustics.db.AppDatabase;
import fr.iut.ecoledesloustics.db.User;

/**
 * Activité qui gère le jeu de la table de multiplication.
 * L'utilisateur doit remplir les réponses aux exercices de multiplication, et le score est mis à jour en fonction du nombre d'erreurs.
 */
public class TableMultiplicationActivity extends AppCompatActivity {

    AppDatabase db;

    public static final String TABLE_KEY = "table_key";

    private TextView[] exerciceTexts;
    private EditText[] exerciceInputs;
    private Button exercice5_validerTable;
    private TextView multiplicationBackButton, utilisateur;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues, les textes des exercices de multiplication
     * et définit les écouteurs d'événements pour les interactions de l'utilisateur.
     *
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multiplication);

        // Récupération du AppDatabase
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ecoledesloustics").build();

        int tableNumber = getIntent().getIntExtra(TABLE_KEY, 1);

        // IDs des TextView et EditText
        int[] textViewIds = {
                R.id.exercice5_text1, R.id.exercice5_text2, R.id.exercice5_text3,
                R.id.exercice5_text4, R.id.exercice5_text5, R.id.exercice5_text6,
                R.id.exercice5_text7, R.id.exercice5_text8, R.id.exercice5_text9,
                R.id.exercice5_text10
        };

        int[] inputIds = {
                R.id.editTextNumber, R.id.editTextNumber1, R.id.editTextNumber2,
                R.id.editTextNumber3, R.id.editTextNumber4, R.id.editTextNumber5,
                R.id.editTextNumber6, R.id.editTextNumber7, R.id.editTextNumber9,
                R.id.editTextNumber10
        };

        exerciceTexts = new TextView[textViewIds.length];
        exerciceInputs = new EditText[inputIds.length];

        // Initialisation des TextView et EditText
        for (int i = 0; i < textViewIds.length; i++) {
            exerciceTexts[i] = findViewById(textViewIds[i]);
            exerciceTexts[i].setText(tableNumber + " x " + (i + 1) + " = ");
            exerciceInputs[i] = findViewById(inputIds[i]);
        }

        // Initialisation des boutons
        exercice5_validerTable = findViewById(R.id.exercice5_validerTable);
        multiplicationBackButton = findViewById(R.id.multiplicationBackButton);
        utilisateur = findViewById(R.id.utilisateur);


        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == RESULT_OK) {
                                    for (int i = 0; i < exerciceInputs.length; i++) {
                                        String inputText = exerciceInputs[i].getText().toString();
                                        int expectedResult = tableNumber * (i + 1);

                                        if (!inputText.isEmpty()) {
                                            int userAnswer = Integer.parseInt(inputText);
                                            if (userAnswer != expectedResult) {
                                                exerciceInputs[i].setTextColor(Color.RED);
                                            } else {
                                                exerciceInputs[i].setTextColor(Color.BLACK);
                                            }
                                        } else {
                                            exerciceInputs[i].setTextColor(Color.RED);
                                        }
                                    }
                                }
                            }});



        multiplicationBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        // Vérification des réponses à la validation
        exercice5_validerTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nbErreurs = 0;
                for (int i = 0; i < exerciceInputs.length; i++) {
                    String inputText = exerciceInputs[i].getText().toString();
                    int expectedResult = tableNumber * (i + 1);

                    if (!inputText.isEmpty()) {
                        int userAnswer = Integer.parseInt(inputText);

                        if (userAnswer != expectedResult) {
                            nbErreurs++;
                        }
                    } else {
                        nbErreurs++;
                    }
                }

                if (nbErreurs > 0) {

                    Intent intent = new Intent(TableMultiplicationActivity.this, ErreurActivity.class);
                    intent.putExtra(ErreurActivity.ERREURS_KEY, nbErreurs);
                    activityResultLauncher.launch(intent);

                } else {
                    // Récupérer le score actuel de l'utilisateur
                    SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
                    int score = sharedPreferences.getInt("UTILISATEUR_SCORE", 0);


                    // Stocker le nouveau score dans SharedPreferences
                    score += 10 - nbErreurs;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("UTILISATEUR_SCORE", score);
                    editor.apply();


                    int finalScore = score;
                    new Thread(() -> {
                        SharedPreferences prefs = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
                        long id = prefs.getLong("UTILISATEUR_ID", -1);
                        if (id != -1) {
                            User user = db.userDao().getById(id);
                            if (user != null) {
                                user.setScore(finalScore);
                                db.userDao().update(user);
                            }
                        }
                    }).start();
                    Intent intent = new Intent(TableMultiplicationActivity.this, FelicitationActivity.class);
                    startActivity(intent);
                }
            }
        });

        afficheUtilisateur();
    }

    /**
     * Méthode pour afficher le prénom de l'utilisateur dans l'interface.
     */
    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }
}
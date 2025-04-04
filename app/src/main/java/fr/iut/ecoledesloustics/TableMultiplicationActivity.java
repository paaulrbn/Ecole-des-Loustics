package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class TableMultiplicationActivity extends AppCompatActivity {

    public static final String TABLE_KEY = "table_key";

    private TextView[] exerciceTexts;
    private EditText[] exerciceInputs;
    private Button exercice5_validerTable;
    private TextView multiplicationBackButton, utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multiplication);


        int tableNumber = getIntent().getIntExtra(TABLE_KEY, 1);
        Log.d("TableMultiplicationActivity", "Valeur de la table : " + tableNumber);

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
                    Intent intent = new Intent(TableMultiplicationActivity.this, FelicitationActivity.class);
                    startActivity(intent);
                }
            }
        });

        afficheUtilisateur();
    }

    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }
}
package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité représentant l'exercice de multiplication.
 * Permet à l'utilisateur de sélectionner un nombre entre 1 et 10 pour démarrer l'exercice de multiplication.
 */
public class MultiplicationActivity extends AppCompatActivity {

    private NumberPicker exercice5_numberPicker;
    private Button exercice5_validerNombreTable;
    private TextView multiplicationBackButton, utilisateur;

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les vues et configure les actions des boutons.
     *
     * @param savedInstanceState L'état sauvegardé de l'activité, s'il existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        // On récupère les vues
        exercice5_numberPicker = findViewById(R.id.exercice5_numberPicker);
        exercice5_validerNombreTable = findViewById(R.id.exercice5_validerNombreTable);
        multiplicationBackButton = findViewById(R.id.multiplicationBackButton);
        utilisateur = findViewById(R.id.utilisateur);

        // Configuration du NumberPicker pour sélectionner un nombre entre 1 et 10
        exercice5_numberPicker.setMinValue(1);
        exercice5_numberPicker.setMaxValue(10);

        // Action pour le bouton "retour"
        multiplicationBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Action pour le bouton "valider nombre"
        exercice5_validerNombreTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiplicationActivity.this, TableMultiplicationActivity.class);
                intent.putExtra(TableMultiplicationActivity.TABLE_KEY, exercice5_numberPicker.getValue());
                startActivity(intent);
            }
        });

        // Affiche l'utilisateur
        afficheUtilisateur();
    }

    /**
     * Affiche le prénom de l'utilisateur à partir des préférences partagées.
     * Si le prénom est trouvé, il est affiché dans la vue utilisateur.
     */
    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }
}

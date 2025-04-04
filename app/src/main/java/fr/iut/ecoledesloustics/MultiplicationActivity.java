package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MultiplicationActivity extends AppCompatActivity {

    private NumberPicker exercice5_numberPicker;
    private Button exercice5_validerNombreTable;
    private TextView multiplicationBackButton, utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_multiplication);

        // On récupère les vues
        exercice5_numberPicker = findViewById(R.id.exercice5_numberPicker);
        exercice5_validerNombreTable = findViewById(R.id.exercice5_validerNombreTable);
        multiplicationBackButton = findViewById(R.id.multiplicationBackButton);
        utilisateur = findViewById(R.id.utilisateur);


        exercice5_numberPicker.setMinValue(1);
        exercice5_numberPicker.setMaxValue(10);

        multiplicationBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        exercice5_validerNombreTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiplicationActivity.this, TableMultiplicationActivity.class);
                intent.putExtra(TableMultiplicationActivity.TABLE_KEY, exercice5_numberPicker.getValue());
                startActivity(intent);
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

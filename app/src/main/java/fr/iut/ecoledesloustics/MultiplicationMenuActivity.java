package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MultiplicationMenuActivity extends AppCompatActivity {

    private TextView additionMenuBackButton, utilisateur;
    private Button commencerMultiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_menu);

        // Récupérer les vues
        additionMenuBackButton = findViewById(R.id.additionMenuBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        commencerMultiplication = findViewById(R.id.commencerMultiplication);

        // Gérer le bouton "retour"
        additionMenuBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Gérer le bouton "commencer multiplication"
        commencerMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiplicationMenuActivity.this, MultiplicationActivity.class);
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
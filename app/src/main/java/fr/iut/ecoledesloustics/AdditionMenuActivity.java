package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdditionMenuActivity extends AppCompatActivity {

    private TextView additionMenuBackButton, utilisateur;
    private Button commencerAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_menu);

        // Récupérer les vues
        additionMenuBackButton = findViewById(R.id.additionMenuBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        commencerAddition = findViewById(R.id.commencerAddition);

        // Gérer le bouton "retour"
        additionMenuBackButton.setOnClickListener(view -> finish());

        // Gérer le bouton "commencer addition"
        commencerAddition.setOnClickListener(view -> {
            Intent intent = new Intent(AdditionMenuActivity.this, AdditionActivity.class);
            startActivity(intent);
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
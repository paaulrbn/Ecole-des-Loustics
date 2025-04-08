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

public class CultureGeneraleMenuActivity extends AppCompatActivity {

    private TextView utilisateur, cultureGeneraleMenuBackButton;
    private Button commencerCultureGeneraleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_generale_menu);

        // Récupérer les vues
        cultureGeneraleMenuBackButton = findViewById(R.id.cultureGeneraleMenuBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        commencerCultureGeneraleButton = findViewById(R.id.commencerCultureGeneraleButton);


        cultureGeneraleMenuBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commencerCultureGeneraleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CultureGeneraleMenuActivity.this, CultureGeneraleActivity.class);
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
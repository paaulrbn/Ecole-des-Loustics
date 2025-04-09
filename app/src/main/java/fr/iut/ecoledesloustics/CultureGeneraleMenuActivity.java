package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité permettant d'accéder au menu de culture générale de l'application.
 * Elle permet de commencer un quiz de culture générale.
 */
public class CultureGeneraleMenuActivity extends AppCompatActivity {

    // Vues
    private TextView utilisateur, cultureGeneraleMenuBackButton;
    private Button commencerCultureGeneraleButton;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues et définit les écouteurs d'événements.
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_generale_menu);

        // Récupérer les vues
        cultureGeneraleMenuBackButton = findViewById(R.id.cultureGeneraleMenuBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        commencerCultureGeneraleButton = findViewById(R.id.commencerCultureGeneraleButton);

        // Gérer le clic sur le bouton pour revenir au menu précédent
        cultureGeneraleMenuBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Gérer le clic sur le bouton pour commencer le quiz de culture générale
        commencerCultureGeneraleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CultureGeneraleMenuActivity.this, CultureGeneraleActivity.class);
                startActivity(intent);
            }
        });

        // Afficher le prénom de l'utilisateur
        afficheUtilisateur();
    }

    /**
     * Méthode pour afficher le prénom de l'utilisateur depuis les SharedPreferences.
     */
    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }
}
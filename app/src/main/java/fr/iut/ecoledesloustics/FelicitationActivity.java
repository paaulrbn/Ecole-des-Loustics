package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité représentant l'écran de félicitations.
 * Permet de féliciter l'utilisateur et de naviguer vers d'autres activités comme le menu principal ou un autre exercice.
 */
public class FelicitationActivity extends AppCompatActivity {

    private Button exercice5_winAutreTableButton;
    private TextView exercice5_mainMenuButton, utilisateur;

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les vues et configure les actions des boutons pour permettre la navigation.
     *
     * @param savedInstanceState L'état sauvegardé de l'activité, s'il existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitation);

        // Récupérer les vues
        exercice5_winAutreTableButton = findViewById(R.id.exercice5_winAutreTableButton);
        exercice5_mainMenuButton = findViewById(R.id.exercice5_mainMenuButton);
        utilisateur = findViewById(R.id.utilisateur);

        // Action pour le bouton "gagner autre table"
        exercice5_winAutreTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FelicitationActivity.this, MultiplicationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Action pour le bouton "menu principal"
        exercice5_mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FelicitationActivity.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité représentant le menu de l'addition.
 * Permet de gérer les interactions avec l'utilisateur pour démarrer une partie d'addition.
 */
public class AdditionMenuActivity extends AppCompatActivity {

    private TextView additionMenuBackButton, utilisateur;
    private Button commencerAddition;

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les vues et configure les écouteurs pour les boutons.
     *
     * @param savedInstanceState L'état sauvegardé de l'activité, s'il existe.
     */
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
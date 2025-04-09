package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.ecoledesloustics.db.User;

/**
 * Activité principale du menu de l'application. Elle permet de naviguer vers différents menus de jeux (multiplication, addition, etc.)
 * et de gérer le changement d'utilisateur.
 */
public class MainMenuActivity extends AppCompatActivity {

    // Vues
    private TextView changeUserButton, accueilText, score;
    private ImageButton imageButtonMultiplication, imageButtonAddition, imageButtonChifoumi,
            imageButtonCultureGenerale;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues, définit les écouteurs d'événements
     * et gère les retours d'activité.
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Récupérer les vues
        changeUserButton = findViewById(R.id.changeUserButton);
        imageButtonMultiplication = findViewById(R.id.imageButtonMultiplication);
        imageButtonAddition = findViewById(R.id.imageButtonAddition);
        imageButtonChifoumi = findViewById(R.id.imageButtonChifoumi);
        imageButtonCultureGenerale = findViewById(R.id.imageButtonCultureGenerale);
        accueilText = findViewById(R.id.accueilText);
        score = findViewById(R.id.score);

        // Créer un ActivityResultLauncher pour gérer le retour d'activité
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d("MainMenuActivity", "onActivityResult: " + result.getResultCode());
                        acceuillirUtilisateur();
                    }
                });

        // Gérer le bouton pour changer d'utilisateur
        changeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Gérer le bouton pour accéder au menu de multiplication
        imageButtonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, MultiplicationMenuActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        // Gérer le bouton pour accéder au menu d'addition
        imageButtonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, AdditionMenuActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        // Gérer le bouton pour accéder au jeu de Chifoumi
        imageButtonChifoumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ChifoumiActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        // Gérer le bouton pour accéder au menu de culture générale
        imageButtonCultureGenerale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, CultureGeneraleMenuActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        acceuillirUtilisateur();
    }

    /**
     * Méthode pour afficher un message d'accueil à l'utilisateur et afficher son score.
     * Elle récupère le prénom et le score de l'utilisateur depuis les SharedPreferences.
     */
    public void acceuillirUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");
        int scoreUser = sharedPreferences.getInt("UTILISATEUR_SCORE", 0);

        if (prenom != null && !prenom.isEmpty()) {
            accueilText.setText("Bienvenue " + prenom + " !");
            score.setText("Score : " + scoreUser);
        } else {
            accueilText.setText("Bienvenue !");
            score.setText("");
        }
    }
}
package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import fr.iut.ecoledesloustics.db.User;

public class MainMenuActivity extends AppCompatActivity {

    // VIEW
    private TextView changeUserButton, accueilText, score;
    private ImageButton imageButtonMultiplication, imageButtonAddition, imageButtonChifoumi,
            imageButtonCultureGenerale;
    private ActivityResultLauncher<Intent> activityResultLauncher;


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
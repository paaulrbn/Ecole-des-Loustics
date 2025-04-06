package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.ecoledesloustics.db.User;

public class MainMenuActivity extends AppCompatActivity {

    // VIEW
    private TextView changeUserButton, accueilText;
    private ImageButton imageButtonMultiplication, imageButtonAddition, imageButtonChifoumi,
            imageButtonCultureGenerale;

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
                startActivity(intent);
            }
        });

        // Gérer le bouton pour accéder au menu d'addition
        imageButtonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, AdditionMenuActivity.class);
                startActivity(intent);
            }
        });

        // Gérer le bouton pour accéder au jeu de Chifoumi
        imageButtonChifoumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ChifoumiActivity.class);
                startActivity(intent);
            }
        });

        // Gérer le bouton pour accéder au menu de culture générale
        imageButtonCultureGenerale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, CultureGeneraleActivity.class);
                startActivity(intent);
            }
        });

        acceuillirUtilisateur();
    }

    public void acceuillirUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            accueilText.setText("Bienvenue " + prenom + " !");
        } else {
            accueilText.setText("Bienvenue !");
        }
    }
}
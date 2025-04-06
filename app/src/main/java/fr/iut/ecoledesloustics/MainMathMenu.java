package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMathMenu extends AppCompatActivity {

    private TextView mathsBackButton, utilisateur;
    private ImageButton imageButtonMultiplication, imageButtonAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_menu);

        // Récupérer les vues
        mathsBackButton = findViewById(R.id.mathsBackButton);
        imageButtonMultiplication = findViewById(R.id.imageButtonMultiplication);
        imageButtonAddition = findViewById(R.id.imageButtonAddition);
        utilisateur = findViewById(R.id.utilisateur);

        // Gérer le bouton retour
        mathsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Gérer le bouton multiplication
        imageButtonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMathMenu.this, MultiplicationActivity.class);
                startActivity(intent);
            }
        });

        // Gérer le bouton addition
        imageButtonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMathMenu.this, AdditionActivity.class);
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
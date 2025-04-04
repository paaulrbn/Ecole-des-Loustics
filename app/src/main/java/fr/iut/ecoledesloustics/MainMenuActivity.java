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

    User user;

    // VIEW
    private TextView changeUserButton, accueilText;
    private ImageButton imageButtonMath;
    private ImageButton imageButtonChifoumi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Récupérer les vues
        changeUserButton = findViewById(R.id.changeUserButton);
        imageButtonMath = findViewById(R.id.imageButtonMath);
        imageButtonChifoumi = findViewById(R.id.imageButtonChifoumi);
        accueilText = findViewById(R.id.accueilText);

        changeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageButtonMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, MainMathMenu.class);
                startActivity(intent);
            }
        });

        imageButtonChifoumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ChifoumiActivity.class);
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
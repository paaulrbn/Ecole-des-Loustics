package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErreurActivity extends AppCompatActivity {

    public static final String ERREURS_KEY = "erreurs_key";
    private TextView exercice5_erreurText, utilisateur;
    private Button exercice5_editButton;
    private Button exercice5_loseAutreTableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur);

        // On récupère les vues
        exercice5_erreurText = findViewById(R.id.exercice5_erreurText);
        exercice5_editButton = findViewById(R.id.exercice5_editButton);
        exercice5_loseAutreTableButton = findViewById(R.id.exercice5_loseAutreTableButton);
        utilisateur = findViewById(R.id.utilisateur);

        int nbErreurs = getIntent().getIntExtra(ERREURS_KEY, 0);
        exercice5_erreurText.setText("Nombre d'erreurs : " + nbErreurs);

        exercice5_editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        exercice5_loseAutreTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ErreurActivity.this, MultiplicationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
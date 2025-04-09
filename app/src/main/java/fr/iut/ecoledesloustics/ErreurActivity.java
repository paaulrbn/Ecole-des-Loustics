package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité représentant la gestion des erreurs.
 * Affiche le nombre d'erreurs effectuées et permet à l'utilisateur de revenir à une autre activité ou de terminer l'exercice.
 */
public class ErreurActivity extends AppCompatActivity {

    public static final String ERREURS_KEY = "erreurs_key";
    private TextView exercice5_erreurText, utilisateur;
    private Button exercice5_editButton;
    private Button exercice5_loseAutreTableButton;

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les vues et configure les actions des boutons.
     *
     * @param savedInstanceState L'état sauvegardé de l'activité, s'il existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur);

        // On récupère les vues
        exercice5_erreurText = findViewById(R.id.exercice5_erreurText);
        exercice5_editButton = findViewById(R.id.exercice5_editButton);
        exercice5_loseAutreTableButton = findViewById(R.id.exercice5_loseAutreTableButton);
        utilisateur = findViewById(R.id.utilisateur);

        // Affichage du nombre d'erreurs
        int nbErreurs = getIntent().getIntExtra(ERREURS_KEY, 0);
        exercice5_erreurText.setText("Nombre d'erreurs : " + nbErreurs);

        // Action sur le bouton "éditer"
        exercice5_editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        // Action sur le bouton "perdre autre table"
        exercice5_loseAutreTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ErreurActivity.this, MultiplicationActivity.class);
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
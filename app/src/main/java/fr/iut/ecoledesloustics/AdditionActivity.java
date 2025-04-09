package fr.iut.ecoledesloustics;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.iut.ecoledesloustics.db.AppDatabase;
import fr.iut.ecoledesloustics.db.User;
import fr.iut.ecoledesloustics.maths.additionData.Addition;
import fr.iut.ecoledesloustics.maths.additionData.SerieAddition;

/**
 * Activité qui gère le jeu d'addition.
 * L'utilisateur doit résoudre des exercices d'addition et soumettre ses réponses.
 * À la fin du jeu, un score est affiché et mis à jour.
 */
public class AdditionActivity extends AppCompatActivity {

    // Données
    private AppDatabase db;

    // Vues
    private TextView questionText, progressText, additionBackButton, utilisateur;
    private EditText answerInput;
    private Button submitButton;

    // Séries d'addition et index actuel
    private SerieAddition serie;
    private int currentIndex = 0;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues, les séries d'addition
     * et définit les écouteurs d'événements pour les interactions de l'utilisateur.
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        // Récupération de la base de données
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ecoledesloustics").build();

        additionBackButton = findViewById(R.id.additionBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        progressText = findViewById(R.id.progress_text);
        questionText = findViewById(R.id.question_text);
        answerInput = findViewById(R.id.answer_input);
        submitButton = findViewById(R.id.submit_button);

        serie = new SerieAddition(true);
        afficherQuestion();

        // Bouton pour revenir à l'activité précédente
        additionBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Bouton pour soumettre la réponse et passer à la question suivante
        submitButton.setOnClickListener(v -> {
            String reponseStr = answerInput.getText().toString().trim();
            if (!reponseStr.isEmpty()) {
                int reponse = Integer.parseInt(reponseStr);
                serie.getAddition(currentIndex).setReponseUtilisateur(reponse);
                currentIndex++;

                // Vérifie si des questions restent à afficher ou si le jeu est terminé
                if (currentIndex < serie.getNombreAdditions()) {
                    answerInput.setText("");
                    afficherQuestion();
                } else {
                    afficherResultat();
                }
            }
        });

        afficheUtilisateur();
    }

    /**
     * Méthode pour afficher la question actuelle de la série d'additions.
     */
    private void afficherQuestion() {
        Addition addition = serie.getAddition(currentIndex);
        questionText.setText(addition.toString());
        progressText.setText("Question " + (currentIndex + 1) + "/" + serie.getNombreAdditions());
    }

    /**
     * Méthode pour afficher le résultat final du jeu et mettre à jour le score.
     */
    private void afficherResultat() {
        int score = serie.getNombreReponsesJustes();
        String message = "Tu as " + score + " bonnes réponses sur " + serie.getNombreAdditions() + " 💪";

        // Récupérer le score actuel de l'utilisateur
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        int scoreUser = sharedPreferences.getInt("UTILISATEUR_SCORE", 0);

        // Stocker le nouveau score dans SharedPreferences
        scoreUser += score;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UTILISATEUR_SCORE", scoreUser);
        editor.apply();

        int finalScoreUser = scoreUser;
        new Thread(() -> {
            SharedPreferences prefs = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
            long id = prefs.getLong("UTILISATEUR_ID", -1);
            if (id != -1) {
                User user = db.userDao().getById(id);
                if (user != null) {
                    user.setScore(finalScoreUser);
                    db.userDao().update(user);
                }
            }
        }).start();

        // Affichage du résultat dans un dialogue
        new AlertDialog.Builder(this)
                .setTitle("Résultat")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    /**
     * Méthode pour afficher le prénom de l'utilisateur dans l'interface.
     */
    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }
}
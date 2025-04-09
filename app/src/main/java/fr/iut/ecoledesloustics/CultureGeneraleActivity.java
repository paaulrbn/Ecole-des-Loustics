package fr.iut.ecoledesloustics;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.iut.ecoledesloustics.db.AppDatabase;
import fr.iut.ecoledesloustics.db.DatabaseClient;
import fr.iut.ecoledesloustics.db.Question;
import fr.iut.ecoledesloustics.db.User;

/**
 * Activité qui gère le quiz de culture générale de l'application.
 * Elle affiche une question à la fois et permet à l'utilisateur de sélectionner une réponse.
 * À la fin du quiz, le score de l'utilisateur est mis à jour.
 */
public class CultureGeneraleActivity extends AppCompatActivity {

    // Données
    private AppDatabase db;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    // Vues
    private TextView questionTextView, utilisateur, cultureGeneraleBackButton, questionCounterTextView;
    private RadioGroup radioGroup;
    private RadioButton reponse1, reponse2, reponse3;
    private Button suivantButton;

    /**
     * Méthode appelée lors de la création de l'activité. Initialise les vues, récupère les questions
     * et définit les écouteurs d'événements pour les interactions de l'utilisateur.
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité, ou null si aucune donnée n'a été sauvegardée.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_generale);

        // Récupération du AppDatabase
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ecoledesloustics").build();

        // Récupérer les vues
        cultureGeneraleBackButton = findViewById(R.id.cultureGeneraleBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        questionTextView = findViewById(R.id.questionTextView);
        questionCounterTextView = findViewById(R.id.questionCounterTextView);
        radioGroup = findViewById(R.id.radioGroup);
        reponse1 = findViewById(R.id.reponse1);
        reponse2 = findViewById(R.id.reponse2);
        reponse3 = findViewById(R.id.reponse3);
        suivantButton = findViewById(R.id.suivantButton);

        // Écouteur pour revenir au menu précédent
        cultureGeneraleBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Écouteur pour le bouton suivant, vérifie la réponse et passe à la question suivante
        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(CultureGeneraleActivity.this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
                    return;
                }
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(questions.get(currentQuestionIndex));
                } else {
                    showResult();
                }
            }
        });

        // Affichage de l'utilisateur actuel
        afficheUtilisateur();

        // Récupération des questions du quiz
        getQuestions();
    }

    /**
     * Méthode pour afficher le prénom de l'utilisateur à partir des SharedPreferences.
     */
    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }

    /**
     * Méthode pour afficher une question à partir de la liste de questions.
     * Les réponses sont mélangées et affichées dans les boutons radio.
     * @param question La question à afficher.
     */
    private void displayQuestion(Question question) {
        questionTextView.setText(question.getTexteQuestion());
        List<String> reponses = Arrays.asList(question.reponse1, question.reponse2, question.reponse3);
        Collections.shuffle(reponses);
        reponse1.setText(reponses.get(0));
        reponse2.setText(reponses.get(1));
        reponse3.setText(reponses.get(2));
        radioGroup.clearCheck();
        questionCounterTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());
    }

    /**
     * Méthode pour vérifier la réponse sélectionnée par l'utilisateur.
     * Si la réponse est correcte, le score est incrémenté.
     */
    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String bonneReponse = questions.get(currentQuestionIndex).bonneReponse;
            if (selectedRadioButton.getText().toString().equals(bonneReponse)) {
                correctAnswers++;
            }
        }
    }

    /**
     * Méthode pour afficher le score final de l'utilisateur et mettre à jour son score dans les SharedPreferences et la base de données.
     */
    private void showResult() {
        // Récupérer le score actuel de l'utilisateur
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        int score = sharedPreferences.getInt("UTILISATEUR_SCORE", 0);

        // Stocker le nouveau score dans SharedPreferences
        score += correctAnswers;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UTILISATEUR_SCORE", score);
        editor.apply();

        // Mettre à jour le score dans la base de données
        new Thread(() -> {
            SharedPreferences prefs = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
            long id = prefs.getLong("UTILISATEUR_ID", -1);
            if (id != -1) {
                User user = db.userDao().getById(id);
                if (user != null) {
                    user.setScore(user.getScore() + correctAnswers);
                    db.userDao().update(user);
                }
            }
        }).start();

        // Affichage du résultat dans une boîte de dialogue
        new AlertDialog.Builder(this)
                .setTitle("Résultat")
                .setMessage("Vous avez " + correctAnswers + " bonnes réponses sur " + questions.size())
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
    }

    /**
     * Méthode pour récupérer les questions du quiz depuis la base de données.
     * Les questions sont récupérées de manière asynchrone et affichées dans l'activité.
     */
    private void getQuestions() {
        new Thread(() -> {
            questions = DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .questionDao()
                    .get10QuestionsAleatoires();

            runOnUiThread(() -> {
                if (questions.isEmpty()) {
                    Toast.makeText(this, "Aucune question disponible", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    displayQuestion(questions.get(0));
                }
            });
        }).start();
    }
}

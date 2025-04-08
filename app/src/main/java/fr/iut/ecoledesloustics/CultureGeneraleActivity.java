package fr.iut.ecoledesloustics;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

public class CultureGeneraleActivity extends AppCompatActivity {

    // DATA
    private AppDatabase db;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    // VIEW
    private TextView questionTextView, utilisateur, cultureGeneraleBackButton, questionCounterTextView;
    private RadioGroup radioGroup;
    private RadioButton reponse1, reponse2, reponse3;
    private Button suivantButton;

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

        cultureGeneraleBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        afficheUtilisateur();
        getQuestions();

    }

    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }

    private void displayQuestion(Question question) {
        Log.d("CultureGeneraleActivity", "Displaying question: " + question.getTexteQuestion());
        questionTextView.setText(question.getTexteQuestion());
        List<String> reponses = Arrays.asList(question.reponse1, question.reponse2, question.reponse3);
        Collections.shuffle(reponses);
        reponse1.setText(reponses.get(0));
        reponse2.setText(reponses.get(1));
        reponse3.setText(reponses.get(2));
        radioGroup.clearCheck();
        questionCounterTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());
    }

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

    private void showResult() {
        new AlertDialog.Builder(this)
                .setTitle("Résultat")
                .setMessage("Vous avez " + correctAnswers + " bonnes réponses sur " + questions.size())
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
    }

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

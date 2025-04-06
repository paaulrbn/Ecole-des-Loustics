package fr.iut.ecoledesloustics;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import fr.iut.ecoledesloustics.db.AppDatabase;
import fr.iut.ecoledesloustics.db.Question;

public class CultureGeneraleActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton reponse1, reponse2, reponse3;
    private Button suivantButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_generale);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ecoledesloustics").build();

        questionTextView = findViewById(R.id.questionTextView);
        radioGroup = findViewById(R.id.radioGroup);
        reponse1 = findViewById(R.id.reponse1);
        reponse2 = findViewById(R.id.reponse2);
        reponse3 = findViewById(R.id.reponse3);
        suivantButton = findViewById(R.id.suivantButton);

        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(questions.get(currentQuestionIndex));
                } else {
                    showResult();
                }
            }
        });

        new GetQuestionsTask().execute();
        new InsertDefaultQuestionsTask().execute();
    }

    private void displayQuestion(Question question) {
        Log.d("CultureGeneraleActivity", "Displaying question: " + question.getTexteQuestion());
        questionTextView.setText(question.getTexteQuestion());
        reponse1.setText(question.reponse1);
        reponse2.setText(question.reponse2);
        reponse3.setText(question.reponse3);
        radioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            int selectedIndex = radioGroup.indexOfChild(selectedRadioButton);
            if (selectedIndex == questions.get(currentQuestionIndex).bonneReponseIndex) {
                correctAnswers++;
            }
        }
    }

    private void showResult() {
        new AlertDialog.Builder(this)
                .setTitle("Résultat")
                .setMessage("Vous avez " + correctAnswers + " bonnes réponses sur " + questions.size())
                .setPositiveButton("OK", null)
                .show();
    }

    private class GetQuestionsTask extends AsyncTask<Void, Void, List<Question>> {
        @Override
        protected List<Question> doInBackground(Void... voids) {
            return db.questionDao().get10QuestionsAleatoires();
        }

        @Override
        protected void onPostExecute(List<Question> result) {
            questions = result;
            if (!questions.isEmpty()) {
                displayQuestion(questions.get(0));
            } else {
                Log.d("CultureGeneraleActivity", "No questions found in the database.");
            }
        }
    }

    private class InsertDefaultQuestionsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            if (db.questionDao().get10QuestionsAleatoires().isEmpty()) {
                db.questionDao().inserer(
                    new Question("Quelle est la capitale de la France?", "Paris", "Londres", "Berlin", 0),
                    new Question("Combien de continents y a-t-il sur Terre?", "5", "6", "7", 2),
                    new Question("Quelle est la plus grande planète du système solaire?", "Terre", "Jupiter", "Mars", 1)
                );
            }
            return null;
        }
    }
}

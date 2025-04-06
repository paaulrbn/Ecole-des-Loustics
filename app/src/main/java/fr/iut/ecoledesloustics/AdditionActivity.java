package fr.iut.ecoledesloustics;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.iut.ecoledesloustics.maths.additionData.Addition;
import fr.iut.ecoledesloustics.maths.additionData.SerieAddition;

public class AdditionActivity extends AppCompatActivity {

    private TextView questionText, progressText, additionBackButton, utilisateur;
    private EditText answerInput;
    private Button submitButton;

    private SerieAddition serie;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        additionBackButton = findViewById(R.id.additionBackButton);
        utilisateur = findViewById(R.id.utilisateur);
        progressText = findViewById(R.id.progress_text);
        questionText = findViewById(R.id.question_text);
        answerInput = findViewById(R.id.answer_input);
        submitButton = findViewById(R.id.submit_button);

        serie = new SerieAddition(true);
        afficherQuestion();

        additionBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submitButton.setOnClickListener(v -> {
            String reponseStr = answerInput.getText().toString().trim();
            if (!reponseStr.isEmpty()) {
                int reponse = Integer.parseInt(reponseStr);
                serie.getAddition(currentIndex).setReponseUtilisateur(reponse);
                currentIndex++;

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

    private void afficherQuestion() {
        Addition addition = serie.getAddition(currentIndex);
        questionText.setText(addition.toString());
        progressText.setText("Question " + (currentIndex + 1) + "/" + serie.getNombreAdditions());
    }

    private void afficherResultat() {
        int score = serie.getNombreReponsesJustes();
        String message = "Tu as " + score + " bonnes réponses sur " + serie.getNombreAdditions() + " 💪";
        new AlertDialog.Builder(this)
                .setTitle("Résultat")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    public void afficheUtilisateur() {
        SharedPreferences sharedPreferences = getSharedPreferences("EcoleDesLousticsPrefs", MODE_PRIVATE);
        String prenom = sharedPreferences.getString("UTILISATEUR_PRENOM", "");

        if (prenom != null && !prenom.isEmpty()) {
            utilisateur.setText(prenom);
        }
    }
}
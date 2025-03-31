package fr.iut.ecoledesloustics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import fr.iut.ecoledesloustics.maths.multiplicationData.Multiplication;
import fr.iut.ecoledesloustics.maths.multiplicationData.TableDeMultiplication;

public class MultiplicationActivity extends AppCompatActivity {

    private Button multiplicationBackButton;
    private TableDeMultiplication table;
    private Multiplication currentMultiplication;
    private TextView multiplicationText;
    private EditText userResponse;
    private TextView resultText;
    private int index = 0; // Index pour parcourir les multiplications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        multiplicationText = findViewById(R.id.multiplicationText);
        userResponse = findViewById(R.id.userResponse);
        Button validateButton = findViewById(R.id.validateButton);
        resultText = findViewById(R.id.resultText);
        multiplicationBackButton = findViewById(R.id.multiplicationBackButton);

        // Initialiser une table de multiplication avec un nombre aléatoire
        Random random = new Random();
        int randomTable = random.nextInt(10) + 1; // Génère un nombre aléatoire entre 1 et 10
        table = new TableDeMultiplication(randomTable, true);
        loadNextMultiplication();

        multiplicationBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Vérifier la réponse
        validateButton.setOnClickListener(view -> checkAnswer());
    }

    private void loadNextMultiplication() {
        if (index < table.getNombreDeMultiplications()) {
            currentMultiplication = table.getMultiplication(index);
            multiplicationText.setText(currentMultiplication.getTerme1() + " x " + currentMultiplication.getTerme2() + " = ?");
            userResponse.setText("");
            resultText.setText("");
            index++;
        } else {
            resultText.setText("Fin de la session !");
        }
    }

    private void checkAnswer() {
        try {
            int response = Integer.parseInt(userResponse.getText().toString());
            currentMultiplication.setReponseUtilisateur(response);

            if (currentMultiplication.isReponseJuste()) {
                resultText.setText("Correct !");
            } else {
                resultText.setText("Incorrect, la bonne réponse est " + (currentMultiplication.getTerme1() * currentMultiplication.getTerme2()));
            }

            loadNextMultiplication(); // Charger l'opération suivante
        } catch (NumberFormatException e) {
            resultText.setText("Veuillez entrer un nombre !");
        }
    }
}
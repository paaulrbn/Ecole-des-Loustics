package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainMathMenu extends AppCompatActivity {

    private Button mathsBackButton;
    private ImageButton imageButtonMultiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_menu);

        // Récupérer les vues
        mathsBackButton = findViewById(R.id.mathsBackButton);
        imageButtonMultiplication = findViewById(R.id.imageButtonMultiplication);

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

    }
}
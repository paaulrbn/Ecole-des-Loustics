package fr.iut.ecoledesloustics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private Button changeUserButton;
    private ImageButton imageButtonMath;
    private ImageButton imageButtonChifoumi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        changeUserButton = findViewById(R.id.changeUserButton);
        imageButtonMath = findViewById(R.id.imageButtonMath);
        imageButtonChifoumi = findViewById(R.id.imageButtonChifoumi);

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
    }
}
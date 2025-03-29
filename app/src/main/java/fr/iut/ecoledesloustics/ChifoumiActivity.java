package fr.iut.ecoledesloustics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.iut.ecoledesloustics.chifoumiData.Jeu;
import fr.iut.ecoledesloustics.chifoumiData.Resultat;


public class ChifoumiActivity extends AppCompatActivity {

    private Jeu jeu;
    private Resultat resultat;

    private Button chifoumiBackButton;
    private TextView exercice3_textViewOrdinateur;
    private TextView exercice3_textViewResultat;
    private TextView exercice3_textViewVictoire;
    private TextView exercice3_textViewDefaite;
    private TextView exercice3_textViewEgalite;

    private ImageButton exercice3_imageButtonOrdinateurPapier;
    private ImageButton exercice3_imageButtonOrdinateurCaillou;
    private ImageButton exercice3_imageButtonOrdinateurCiseaux;

    private ImageButton exercice3_imageButtonPapier;
    private ImageButton exercice3_imageButtonCaillou;
    private ImageButton exercice3_imageButtonCiseaux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chifoumi);

        // Initialisation des objets
        resultat = new Resultat();

        // Récupération des vues
        exercice3_textViewOrdinateur = findViewById(R.id.exercice3_textViewOrdinateur);
        exercice3_textViewResultat = findViewById(R.id.exercice3_textViewResultat);
        exercice3_textViewVictoire = findViewById(R.id.exercice3_textViewVictoire);
        exercice3_textViewDefaite = findViewById(R.id.exercice3_textViewDefaite);
        exercice3_textViewEgalite = findViewById(R.id.exercice3_textViewEgalite);
        chifoumiBackButton = findViewById(R.id.chifoumiBackButton);

        // Boutons de l'ordinateur (invisibles au début)
        exercice3_imageButtonOrdinateurPapier = findViewById(R.id.imageButtonPapier);
        exercice3_imageButtonOrdinateurCaillou = findViewById(R.id.imageButtonCailloux);
        exercice3_imageButtonOrdinateurCiseaux = findViewById(R.id.imageButtonCiseaux);

        // Boutons de l'utilisateur
        exercice3_imageButtonPapier = findViewById(R.id.imageButton4);
        exercice3_imageButtonCaillou = findViewById(R.id.imageButton5);
        exercice3_imageButtonCiseaux = findViewById(R.id.imageButton6);

        // Gérer le bouton de retour
        chifoumiBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Gestion des clics des boutons utilisateur
        exercice3_imageButtonPapier.setOnClickListener(v -> jouer(Jeu.PAPIER));
        exercice3_imageButtonCaillou.setOnClickListener(v -> jouer(Jeu.CAILLOUX));
        exercice3_imageButtonCiseaux.setOnClickListener(v -> jouer(Jeu.CISEAUX));
    }

    private void jouer(int choixJoueur) {
        jeu = new Jeu(); // Génération de la main de l'ordinateur
        jeu.setMainJoueur(choixJoueur);

        // Réinitialisation des couleurs des boutons utilisateur
        exercice3_imageButtonPapier.setBackgroundColor(0xFF000000);
        exercice3_imageButtonCaillou.setBackgroundColor(0xFF000000);
        exercice3_imageButtonCiseaux.setBackgroundColor(0xFF000000);

        // Mise en rouge du bouton sélectionné
        if (choixJoueur == Jeu.PAPIER) {
            exercice3_imageButtonPapier.setBackgroundColor(0xFFFF0000);
        } else if (choixJoueur == Jeu.CAILLOUX) {
            exercice3_imageButtonCaillou.setBackgroundColor(0xFFFF0000);
        } else if (choixJoueur == Jeu.CISEAUX) {
            exercice3_imageButtonCiseaux.setBackgroundColor(0xFFFF0000);
        }

        exercice3_imageButtonOrdinateurPapier.setVisibility(View.INVISIBLE);
        exercice3_imageButtonOrdinateurCaillou.setVisibility(View.INVISIBLE);
        exercice3_imageButtonOrdinateurCiseaux.setVisibility(View.INVISIBLE);

        if (jeu.getMainOrdinateur() == Jeu.PAPIER) {
            exercice3_imageButtonOrdinateurPapier.setVisibility(View.VISIBLE);
        } else if (jeu.getMainOrdinateur() == Jeu.CAILLOUX) {
            exercice3_imageButtonOrdinateurCaillou.setVisibility(View.VISIBLE);
        } else if (jeu.getMainOrdinateur() == Jeu.CISEAUX) {
            exercice3_imageButtonOrdinateurCiseaux.setVisibility(View.VISIBLE);
        }

        // Mise à jour du texte de la main de l'ordinateur
        String[] mains = {"Caillou", "Ciseaux", "Papier"};
        exercice3_textViewOrdinateur.setText("Main de l'ordinateur : " + mains[jeu.getMainOrdinateur()]);

        // Vérification du résultat
        if (jeu.egalite()) {
            exercice3_textViewResultat.setText("Égalité !");
            resultat.addEgalite();
        } else if (jeu.joueurGagne()) {
            exercice3_textViewResultat.setText("Victoire !");
            resultat.addVictoire();
        } else {
            exercice3_textViewResultat.setText("Défaite !");
            resultat.addDefaite();
        }

        // Mise à jour des scores
        exercice3_textViewVictoire.setText(String.valueOf(resultat.getNombreVictoire()));
        exercice3_textViewDefaite.setText(String.valueOf(resultat.getNombreDefaite()));
        exercice3_textViewEgalite.setText(String.valueOf(resultat.getNombreEgalite()));
    }
}
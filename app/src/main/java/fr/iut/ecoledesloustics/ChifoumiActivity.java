package fr.iut.ecoledesloustics;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.ecoledesloustics.chifoumiData.Jeu;
import fr.iut.ecoledesloustics.chifoumiData.Resultat;

/**
 * Activité représentant le jeu du Chifoumi.
 * Permet à l'utilisateur de jouer contre l'ordinateur au jeu de pierre-papier-ciseaux.
 * Affiche les choix des joueurs, le résultat de chaque partie et les scores.
 */
public class ChifoumiActivity extends AppCompatActivity {

    private Jeu jeu;
    private Resultat resultat;

    private TextView chifoumiBackButton, exercice3_textViewOrdinateur, exercice3_textViewResultat,
            exercice3_textViewVictoire, exercice3_textViewDefaite, exercice3_textViewEgalite,
            utilisateur;

    private ImageButton exercice3_imageButtonOrdinateurPapier,
            exercice3_imageButtonOrdinateurCaillou,
            exercice3_imageButtonOrdinateurCiseaux;

    private ImageButton exercice3_imageButtonPapier, exercice3_imageButtonCaillou,
            exercice3_imageButtonCiseaux;

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les vues et les objets nécessaires au jeu.
     * Configure les boutons et les actions associées.
     *
     * @param savedInstanceState L'état sauvegardé de l'activité, s'il existe.
     */
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
        utilisateur = findViewById(R.id.utilisateur);

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

        // Affichage de l'utilisateur
        afficheUtilisateur();

        // Gestion des clics des boutons utilisateur
        exercice3_imageButtonPapier.setOnClickListener(v -> jouer(Jeu.PAPIER));
        exercice3_imageButtonCaillou.setOnClickListener(v -> jouer(Jeu.CAILLOUX));
        exercice3_imageButtonCiseaux.setOnClickListener(v -> jouer(Jeu.CISEAUX));
    }

    /**
     * Gère le déroulement du jeu lorsque l'utilisateur fait un choix.
     * L'ordinateur fait également un choix, et le résultat est déterminé.
     * Le score et l'affichage sont mis à jour.
     *
     * @param choixJoueur Le choix de l'utilisateur : Pierre, Papier ou Ciseaux.
     */
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
package fr.iut.ecoledesloustics.chifoumiData;

import java.util.Random;

/**
 * Représente un jeu de Chifoumi (Pierre-papier-ciseaux).
 * Permet de gérer les choix du joueur et de l'ordinateur, ainsi que de déterminer les résultats (égalité, victoire).
 */
public class Jeu {

    public static final int CAILLOUX = 0;
    public static final int CISEAUX = 1;
    public static final int PAPIER = 2;
    private static final Random random = new Random();

    private int mainJoueur;
    private int mainOrdinateur;

    /**
     * Constructeur de la classe Jeu.
     * Initialise la main de l'ordinateur en appelant la méthode initMainOrdinateur().
     */
    public Jeu() {
        initMainOrdinateur();
    }

    /**
     * Retourne la main du joueur.
     *
     * @return La main du joueur.
     */
    public int getMainJoueur() {
        return mainJoueur;
    }

    /**
     * Définit la main du joueur.
     *
     * @param mainJoueur La main à attribuer au joueur.
     */
    public void setMainJoueur(int mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    /**
     * Retourne la main de l'ordinateur.
     *
     * @return La main de l'ordinateur.
     */
    public int getMainOrdinateur() {
        return mainOrdinateur;
    }

    /**
     * Définit la main de l'ordinateur.
     *
     * @param mainOrdinateur La main à attribuer à l'ordinateur.
     */
    private void setMainOrdinateur(int mainOrdinateur) {
        this.mainOrdinateur = mainOrdinateur;
    }

    /**
     * Initialise la main de l'ordinateur avec un choix aléatoire parmi Cailloux, Ciseaux et Papier.
     */
    private void initMainOrdinateur() {
        mainOrdinateur = random.nextInt(3);
    }

    /**
     * Détermine si la partie est une égalité (si la main du joueur est la même que celle de l'ordinateur).
     *
     * @return true si la partie est une égalité, false sinon.
     */
    public boolean egalite() {
        return mainJoueur == mainOrdinateur;
    }

    /**
     * Détermine si le joueur a gagné (selon les règles de Chifoumi).
     *
     * @return true si le joueur a gagné, false sinon.
     */
    public boolean joueurGagne() {
        return (mainJoueur == CAILLOUX && mainOrdinateur == CISEAUX)
                || (mainJoueur == CISEAUX && mainOrdinateur == PAPIER)
                || (mainJoueur == PAPIER && mainOrdinateur == CAILLOUX);
    }

}

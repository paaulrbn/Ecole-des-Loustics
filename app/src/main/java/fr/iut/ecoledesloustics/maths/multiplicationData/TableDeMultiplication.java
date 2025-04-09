package fr.iut.ecoledesloustics.maths.multiplicationData;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Représente une table de multiplication.
 * Contient les multiplications associées à un nombre de table donné et permet de les gérer (initialisation, mélange, comptage des réponses justes).
 */
public class TableDeMultiplication {
    private int nombreTable;
    private ArrayList<Multiplication> multiplications;

    /**
     * Constructeur de la classe TableDeMultiplication.
     * Initialise la table de multiplication avec le nombre de table donné et une liste de multiplications.
     * Si le paramètre estMelange est vrai, les multiplications sont mélangées.
     *
     * @param nombreTable Le nombre de table pour cette série de multiplications.
     * @param estMelange Indique si la liste de multiplications doit être mélangée ou non.
     */
    public TableDeMultiplication(int nombreTable, boolean estMelange) {
        this.nombreTable = nombreTable;
        this.multiplications = new ArrayList<>();
        initialisation();
        if (estMelange) {
            melange();
        }
    }

    /**
     * Initialise la liste des multiplications pour la table de multiplication en cours.
     * Crée une multiplication pour chaque valeur de 1 à 10 avec le nombre de table spécifié.
     */
    private void initialisation() {
        for (int i = 1; i <= 10; i++) {
            multiplications.add(new Multiplication(nombreTable, i));
        }
    }

    /**
     * Mélange les multiplications dans la liste de manière aléatoire.
     */
    private void melange() {
        Collections.shuffle(multiplications);
    }

    /**
     * Retourne le nombre de réponses justes dans la table de multiplication.
     *
     * @return Le nombre de réponses justes.
     */
    public int getNombreReponsesJustes() {
        int count = 0;
        for (Multiplication m : multiplications) {
            if (m.isReponseJuste()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Retourne le nombre total de multiplications dans la table.
     *
     * @return Le nombre de multiplications.
     */
    public int getNombreDeMultiplications() {
        return multiplications.size();
    }

    /**
     * Retourne la multiplication à l'index spécifié.
     *
     * @param index L'index de la multiplication à récupérer.
     * @return La multiplication à l'index donné, ou null si l'index est invalide.
     */
    public Multiplication getMultiplication(int index) {
        if (index >= 0 && index < multiplications.size()) {
            return multiplications.get(index);
        }
        return null;
    }
}
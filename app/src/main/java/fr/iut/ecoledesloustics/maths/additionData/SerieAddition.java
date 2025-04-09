package fr.iut.ecoledesloustics.maths.additionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente une série d'additions.
 * Contient une liste d'objets Addition et fournit des méthodes pour gérer cette série, comme l'accès aux additions,
 * le comptage des réponses justes et l'obtention du nombre d'additions.
 */
public class SerieAddition {
    private List<Addition> additions;

    /**
     * Constructeur de la série d'additions.
     * Crée une série de 10 additions avec des termes aléatoires entre 0 et 9.
     * Si le paramètre estMelange est vrai, les additions sont mélangées.
     *
     * @param estMelange Indique si la série d'additions doit être mélangée ou non.
     */
    public SerieAddition(boolean estMelange) {
        additions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int t1 = (int)(Math.random() * 10);
            int t2 = (int)(Math.random() * 10);
            additions.add(new Addition(t1, t2));
        }
        if (estMelange) {
            Collections.shuffle(additions);
        }
    }

    /**
     * Retourne l'addition à l'index spécifié dans la série.
     *
     * @param index L'index de l'addition à récupérer.
     * @return L'addition à l'index spécifié.
     */
    public Addition getAddition(int index) {
        return additions.get(index);
    }

    /**
     * Retourne le nombre de réponses justes dans la série d'additions.
     *
     * @return Le nombre de réponses justes.
     */
    public int getNombreReponsesJustes() {
        int count = 0;
        for (Addition a : additions) {
            if (a.isReponseJuste()) count++;
        }
        return count;
    }

    /**
     * Retourne le nombre total d'additions dans la série.
     *
     * @return Le nombre d'additions.
     */
    public int getNombreAdditions() {
        return additions.size();
    }
}

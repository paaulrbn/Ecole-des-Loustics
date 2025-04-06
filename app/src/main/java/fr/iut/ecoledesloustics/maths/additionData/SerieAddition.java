package fr.iut.ecoledesloustics.maths.additionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerieAddition {
    private List<Addition> additions;

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

    public Addition getAddition(int index) {
        return additions.get(index);
    }

    public int getNombreReponsesJustes() {
        int count = 0;
        for (Addition a : additions) {
            if (a.isReponseJuste()) count++;
        }
        return count;
    }

    public int getNombreAdditions() {
        return additions.size();
    }
}

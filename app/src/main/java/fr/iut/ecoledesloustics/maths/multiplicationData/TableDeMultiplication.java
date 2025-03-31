package fr.iut.ecoledesloustics.maths.multiplicationData;

import java.util.ArrayList;
import java.util.Collections;

public class TableDeMultiplication {
    private int nombreTable;
    private ArrayList<Multiplication> multiplications;

    public TableDeMultiplication(int nombreTable, boolean estMelange) {
        this.nombreTable = nombreTable;
        this.multiplications = new ArrayList<>();
        initialisation();
        if (estMelange) {
            melange();
        }
    }

    private void initialisation() {
        for (int i = 1; i <= 10; i++) {
            multiplications.add(new Multiplication(nombreTable, i));
        }
    }

    private void melange() {
        Collections.shuffle(multiplications);
    }

    public int getNombreReponsesJustes() {
        int count = 0;
        for (Multiplication m : multiplications) {
            if (m.isReponseJuste()) {
                count++;
            }
        }
        return count;
    }

    public int getNombreDeMultiplications() {
        return multiplications.size();
    }

    public Multiplication getMultiplication(int index) {
        if (index >= 0 && index < multiplications.size()) {
            return multiplications.get(index);
        }
        return null;
    }
}
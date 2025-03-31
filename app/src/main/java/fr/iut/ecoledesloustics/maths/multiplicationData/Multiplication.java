package fr.iut.ecoledesloustics.maths.multiplicationData;

public class Multiplication {
    private int terme1, terme2;
    private Integer reponseUtilisateur;

    public Multiplication(int terme1, int terme2) {
        this.terme1 = terme1;
        this.terme2 = terme2;
    }

    public int getTerme1() {
        return terme1;
    }

    public int getTerme2() {
        return terme2;
    }

    public void setReponseUtilisateur(Integer reponseUtilisateur) {
        this.reponseUtilisateur = reponseUtilisateur;
    }

    public boolean isReponseJuste() {
        return reponseUtilisateur == terme1 * terme2;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "terme1=" + terme1 +
                ", terme2=" + terme2 +
                ", reponseUtilisateur=" + reponseUtilisateur +
                '}';
    }
}

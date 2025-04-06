package fr.iut.ecoledesloustics.maths.additionData;

public class Addition {
    private int terme1;
    private int terme2;
    private Integer reponseUtilisateur;

    public Addition(int terme1, int terme2) {
        this.terme1 = terme1;
        this.terme2 = terme2;
    }

    public void setReponseUtilisateur(int reponse) {
        this.reponseUtilisateur = reponse;
    }

    public boolean isReponseJuste() {
        return reponseUtilisateur != null && reponseUtilisateur == (terme1 + terme2);
    }

    @Override
    public String toString() {
        return terme1 + " + " + terme2 + " = ?";
    }

    public int getTerme1() {
        return terme1;
    }

    public int getTerme2() {
        return terme2;
    }
}
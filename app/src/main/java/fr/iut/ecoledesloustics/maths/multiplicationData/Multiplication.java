package fr.iut.ecoledesloustics.maths.multiplicationData;

/**
 * Représente une multiplication de deux termes.
 * Permet de stocker les termes, la réponse de l'utilisateur, et de vérifier si la réponse est correcte.
 */
public class Multiplication {
    private int terme1, terme2;
    private Integer reponseUtilisateur;

    /**
     * Constructeur de la classe Multiplication.
     * Initialise les deux termes de la multiplication.
     *
     * @param terme1 Le premier terme de la multiplication.
     * @param terme2 Le deuxième terme de la multiplication.
     */
    public Multiplication(int terme1, int terme2) {
        this.terme1 = terme1;
        this.terme2 = terme2;
    }

    /**
     * Retourne le premier terme de la multiplication.
     *
     * @return Le premier terme de la multiplication.
     */
    public int getTerme1() {
        return terme1;
    }

    /**
     * Retourne le deuxième terme de la multiplication.
     *
     * @return Le deuxième terme de la multiplication.
     */
    public int getTerme2() {
        return terme2;
    }

    /**
     * Définit la réponse de l'utilisateur pour la multiplication.
     *
     * @param reponseUtilisateur La réponse de l'utilisateur.
     */
    public void setReponseUtilisateur(Integer reponseUtilisateur) {
        this.reponseUtilisateur = reponseUtilisateur;
    }

    /**
     * Vérifie si la réponse de l'utilisateur est correcte.
     *
     * @return true si la réponse de l'utilisateur est correcte, false sinon.
     */
    public boolean isReponseJuste() {
        return reponseUtilisateur == terme1 * terme2;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la multiplication.
     *
     * @return Une chaîne représentant la multiplication sous la forme "terme1 * terme2 = ?".
     */
    @Override
    public String toString() {
        return "Multiplication{" +
                "terme1=" + terme1 +
                ", terme2=" + terme2 +
                ", reponseUtilisateur=" + reponseUtilisateur +
                '}';
    }
}

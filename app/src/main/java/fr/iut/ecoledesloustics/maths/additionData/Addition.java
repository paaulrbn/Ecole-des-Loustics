package fr.iut.ecoledesloustics.maths.additionData;

/**
 * Représente une addition de deux termes.
 * Permet de stocker les termes, la réponse de l'utilisateur, et de vérifier si la réponse est correcte.
 */
public class Addition {
    private int terme1;
    private int terme2;
    private Integer reponseUtilisateur;

    /**
     * Constructeur de la classe Addition.
     * Initialise les deux termes de l'addition.
     *
     * @param terme1 Le premier terme de l'addition.
     * @param terme2 Le deuxième terme de l'addition.
     */
    public Addition(int terme1, int terme2) {
        this.terme1 = terme1;
        this.terme2 = terme2;
    }

    /**
     * Définit la réponse de l'utilisateur pour l'addition.
     *
     * @param reponse La réponse de l'utilisateur.
     */
    public void setReponseUtilisateur(int reponse) {
        this.reponseUtilisateur = reponse;
    }

    /**
     * Vérifie si la réponse de l'utilisateur est correcte.
     *
     * @return true si la réponse de l'utilisateur est correcte, false sinon.
     */
    public boolean isReponseJuste() {
        return reponseUtilisateur != null && reponseUtilisateur == (terme1 + terme2);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'addition.
     *
     * @return Une chaîne représentant l'addition sous la forme "terme1 + terme2 = ?".
     */
    @Override
    public String toString() {
        return terme1 + " + " + terme2 + " = ?";
    }

    /**
     * Retourne le premier terme de l'addition.
     *
     * @return Le premier terme de l'addition.
     */
    public int getTerme1() {
        return terme1;
    }

    /**
     * Retourne le deuxième terme de l'addition.
     *
     * @return Le deuxième terme de l'addition.
     */
    public int getTerme2() {
        return terme2;
    }
}
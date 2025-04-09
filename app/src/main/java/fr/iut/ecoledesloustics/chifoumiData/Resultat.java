package fr.iut.ecoledesloustics.chifoumiData;

/**
 * Représente le résultat d'une partie de Chifoumi.
 * Contient le nombre de victoires, égalités et défaites du joueur.
 * Permet de manipuler ces valeurs en les incrémentant ou en les récupérant.
 */
public class Resultat {

	private int nombreVictoire = 0;
	private int nombreEgalite = 0;
	private int nombreDefaite = 0;

    /**
     * Retourne le nombre de victoires du joueur.
     *
     * @return Le nombre de victoires.
     */
	public int getNombreVictoire() {
		return nombreVictoire;
	}

    /**
     * Définit le nombre de victoires du joueur.
     *
     * @param nombreVictoire Le nombre de victoires à attribuer.
     */
	public void setNombreVictoire(int nombreVictoire) {
		this.nombreVictoire = nombreVictoire;
	}

    /**
     * Retourne le nombre d'égalités du joueur.
     *
     * @return Le nombre d'égalités.
     */
	public int getNombreEgalite() {
		return nombreEgalite;
	}

    /**
     * Définit le nombre d'égalités du joueur.
     *
     * @param nombreEgalite Le nombre d'égalités à attribuer.
     */
	public void setNombreEgalite(int nombreEgalite) {
		this.nombreEgalite = nombreEgalite;
	}

    /**
     * Retourne le nombre de défaites du joueur.
     *
     * @return Le nombre de défaites.
     */
	public int getNombreDefaite() {
		return nombreDefaite;
	}

    /**
     * Définit le nombre de défaites du joueur.
     *
     * @param nombreDefaite Le nombre de défaites à attribuer.
     */
	public void setNombreDefaite(int nombreDefaite) {
		this.nombreDefaite = nombreDefaite;
	}


    /**
     * Incrémente le nombre de victoires du joueur.
     */
	public void addVictoire() {
		nombreVictoire++;
	}

    /**
     * Incrémente le nombre de défaites du joueur.
     */
	public void addDefaite() {
		nombreDefaite++;
	}

    /**
     * Incrémente le nombre d'égalités du joueur.
     */
	public void addEgalite() {
		nombreEgalite++;
	}
}

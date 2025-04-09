package fr.iut.ecoledesloustics.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Représente un utilisateur dans la base de données.
 * Contient les informations personnelles de l'utilisateur et son score.
 */
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String prenom;
    private String nom;
    private int score;

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public long getId() {
        return id;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le score de l'utilisateur.
     *
     * @return Le score de l'utilisateur.
     */
    public int getScore() {
        return score;
    }

    /**
     * Définit le score de l'utilisateur.
     *
     * @param score Le score à attribuer à l'utilisateur.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param id L'identifiant à attribuer à l'utilisateur.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Définit le prénom de l'utilisateur.
     *
     * @param prenom Le prénom à attribuer à l'utilisateur.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Définit le nom de l'utilisateur.
     *
     * @param nom Le nom à attribuer à l'utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}

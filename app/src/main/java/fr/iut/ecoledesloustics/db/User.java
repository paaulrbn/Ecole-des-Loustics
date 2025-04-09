package fr.iut.ecoledesloustics.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String prenom;
    private String nom;
    private int score;


    // Getters et Setters
    public long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

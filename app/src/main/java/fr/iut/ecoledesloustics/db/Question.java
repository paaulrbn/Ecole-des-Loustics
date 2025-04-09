package fr.iut.ecoledesloustics.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Représente une question dans la base de données.
 * Contient les informations relatives à une question et ses réponses possibles.
 */
@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String texteQuestion;
    public String reponse1;
    public String reponse2;
    public String reponse3;
    public String bonneReponse; // 0, 1 ou 2

    /**
     * Constructeur par défaut.
     * Initialise une nouvelle question sans valeurs définies.
     */
    public Question() {}

    /**
     * Constructeur avec paramètres.
     * Initialise une nouvelle question avec les valeurs fournies.
     *
     * @param texteQuestion Le texte de la question.
     * @param reponse1 La première réponse possible.
     * @param reponse2 La deuxième réponse possible.
     * @param reponse3 La troisième réponse possible.
     * @param bonneReponse la bonne réponse.
     */
    public Question(String texteQuestion, String reponse1, String reponse2, String reponse3, String bonneReponse) {
        this.texteQuestion = texteQuestion;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponse = bonneReponse;
    }

    /**
     * Retourne le texte de la question.
     *
     * @return Le texte de la question.
     */
    public String getTexteQuestion() {
        return texteQuestion;
    }
}

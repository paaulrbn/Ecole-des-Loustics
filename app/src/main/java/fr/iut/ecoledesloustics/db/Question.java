package fr.iut.ecoledesloustics.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String texteQuestion;
    public String reponse1;
    public String reponse2;
    public String reponse3;
    public int bonneReponseIndex; // 0, 1 ou 2

    public Question(String texteQuestion, String reponse1, String reponse2, String reponse3, int bonneReponseIndex) {
        this.texteQuestion = texteQuestion;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponseIndex = bonneReponseIndex;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }
}

package fr.iut.ecoledesloustics.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM Question ORDER BY RANDOM() LIMIT 10")
    List<Question> get10QuestionsAleatoires();

    @Query("SELECT COUNT(*) FROM Question")
    int countQuestions();

    @Insert
    void inserer(Question... questions);
}

package fr.iut.ecoledesloustics.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Interface représentant le Data Access Object (DAO) pour la table Question.
 * Permet d'effectuer des opérations de lecture et d'écriture sur la base de données concernant les questions.
 */
@Dao
public interface QuestionDao {

    /**
     * Récupère 10 questions aléatoires dans la base de données.
     *
     * @return Une liste de 10 questions sélectionnées aléatoirement.
     */
    @Query("SELECT * FROM Question ORDER BY RANDOM() LIMIT 10")
    List<Question> get10QuestionsAleatoires();

    /**
     * Compte le nombre total de questions dans la base de données.
     *
     * @return Le nombre total de questions.
     */
    @Query("SELECT COUNT(*) FROM Question")
    int countQuestions();

    /**
     * Insère une ou plusieurs questions dans la base de données.
     *
     * @param questions Les questions à insérer.
     */
    @Insert
    void inserer(Question... questions);
}

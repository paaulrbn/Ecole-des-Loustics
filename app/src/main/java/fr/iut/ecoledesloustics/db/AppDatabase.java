package fr.iut.ecoledesloustics.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Classe représentant la base de données de l'application.
 * Elle étend RoomDatabase et contient les DAO pour accéder aux entités User et Question.
 * Cette classe gère la version de la base de données et la création des instances de DAO.
 */
@Database(entities = {User.class, Question.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Retourne le DAO pour l'entité User.
     * Permet d'effectuer des opérations de lecture, écriture, mise à jour et suppression sur la table User.
     *
     * @return Le DAO pour l'entité User.
     */
    public abstract UserDAO userDao();

    /**
     * Retourne le DAO pour l'entité Question.
     * Permet d'effectuer des opérations de lecture, écriture, mise à jour et suppression sur la table Question.
     *
     * @return Le DAO pour l'entité Question.
     */
    public abstract QuestionDao questionDao();
}
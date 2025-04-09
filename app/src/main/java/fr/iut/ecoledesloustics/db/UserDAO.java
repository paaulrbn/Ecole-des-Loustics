package fr.iut.ecoledesloustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Interface représentant le Data Access Object (DAO) pour la table User.
 * Permet d'effectuer des opérations de lecture, insertion, mise à jour et suppression sur la base de données concernant les utilisateurs.
 */
@Dao
public interface UserDAO {

    /**
     * Récupère tous les utilisateurs de la base de données.
     *
     * @return Une liste contenant tous les utilisateurs.
     */
    @Query("SELECT * FROM user")
    List<User> getAll();

    /**
     * Récupère un utilisateur à partir de son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM user WHERE id = :id")
    User getById(long id);

    /**
     * Insère un utilisateur dans la base de données.
     *
     * @param user L'utilisateur à insérer.
     * @return L'identifiant de l'utilisateur inséré.
     */
    @Insert
    long insert(User user);

    /**
     * Insère plusieurs utilisateurs dans la base de données.
     *
     * @param users Les utilisateurs à insérer.
     * @return Les identifiants des utilisateurs insérés.
     */
    @Insert
    long[] insertAll(User... users);

    /**
     * Supprime un utilisateur de la base de données.
     *
     * @param task L'utilisateur à supprimer.
     */
    @Delete
    void delete(User task);

    /**
     * Met à jour un utilisateur dans la base de données.
     *
     * @param task L'utilisateur à mettre à jour.
     */
    @Update
    void update(User task);
}

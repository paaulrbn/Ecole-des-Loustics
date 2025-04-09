package fr.iut.ecoledesloustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(long id);

    @Insert
    long insert(User user);

    @Insert
    long[] insertAll(User... users);

    @Delete
    void delete(User task);

    @Update
    void update(User task);
}

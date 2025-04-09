package fr.iut.ecoledesloustics.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Question.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDao();
    public abstract QuestionDao questionDao();
}
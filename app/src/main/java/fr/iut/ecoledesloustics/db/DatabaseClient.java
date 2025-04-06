package fr.iut.ecoledesloustics.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        // MyToDos est le nom de la base de données
        //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyToDos").build();

        ////////// REMPLIR LA BD à la première création à l'aide de l'objet roomDatabaseCallback
        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "ecoledesloustics").fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insertion des données par défaut dans la base
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Quelle est la forme correcte du verbe ''être'' à la première personne du singulier au présent ?', 'Suis', 'Es', 'Est', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Quel est le contraire de ''heureux'' ?', 'Triste', 'Content', 'Joyeux', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Quel est l''antonyme de ''grand'' ?', 'Petit', 'Minuscule', 'Énorme', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Comment se conjugue ''avoir'' à la troisième personne du singulier au présent ?', 'A', 'Avoir', 'Ai', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Quel est le féminin de ''acteur'' ?', 'Actrice', 'Acteur', 'Acteuse', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Que signifie le mot ''égard'' ?', 'Respect', 'Discrétion', 'Mépris', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Quel est le synonyme de ''intelligent'' ?', 'Astucieux', 'Bête', 'Débile', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Comment conjuguer le verbe ''manger'' à la première personne du pluriel au présent ?', 'Mangeons', 'Mangeait', 'Mangé', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Quel est le contraire de ''chaud'' ?', 'Froid', 'Tiède', 'Piquant', 0);");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponseIndex) VALUES ('Que veut dire le mot ''plutôt'' ?', 'Sauf', 'Avant', 'Aussi', 0);");
        }
    };
}


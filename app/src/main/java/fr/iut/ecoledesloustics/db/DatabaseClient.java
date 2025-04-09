package fr.iut.ecoledesloustics.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Classe qui fournit un accès centralisé à la base de données de l'application en utilisant Room.
 * Elle permet de récupérer l'instance de la base de données et d'effectuer des opérations sur les entités.
 */
public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    /**
     * Constructeur privé pour initialiser la base de données avec le contexte fourni.
     * @param context Le contexte de l'application.
     */
    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ecoledesloustics").build();
    }

    /**
     * Méthode pour obtenir l'instance unique de DatabaseClient. Si l'instance n'existe pas, elle est créée.
     * @param context Le contexte de l'application utilisé pour créer la base de données.
     * @return L'instance unique de DatabaseClient.
     */
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    /**
     * Méthode pour obtenir l'instance de la base de données.
     * @return L'instance de la base de données.
     */
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
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quelle est la forme correcte du verbe ''être'' à la première personne du singulier au présent ?', 'Suis', 'Es', 'Est', 'Suis');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est l''antonyme de ''grand'' ?', 'Petit', 'Minuscule', 'Énorme', 'Petit');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Comment se conjugue ''avoir'' à la troisième personne du singulier au présent ?', 'A', 'Avoir', 'Ai', 'A');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Que signifie le mot ''égard'' ?', 'Respect', 'Discrétion', 'Mépris', 'Respect');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Comment conjuguer le verbe ''manger'' à la première personne du pluriel au présent ?', 'Mangeons', 'Mangeait', 'Mangé', 'Mangeons');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Combien de côtés a un triangle ?', '3', '4', '5', '3');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est l''infinitif du verbe ''chantait'' ?', 'Chanter', 'Chanté', 'Chante', 'Chanter');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Combien font 6 x 4 ?', '24', '20', '26', '24');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est le féminin de ''boulanger'' ?', 'Boulangère', 'Boulangèreuse', 'Boulangeuse', 'Boulangère');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est le nom du président de la France en 2025 ?', 'Macron', 'Sarkozy', 'Hollande', 'Macron');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quelle est la capitale de l''Espagne ?', 'Madrid', 'Barcelone', 'Lisbonne', 'Madrid');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Comment appelle-t-on un mot qui a le même sens qu’un autre ?', 'Synonyme', 'Antonyme', 'Adjectif', 'Synonyme');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est le résultat de 100 - 37 ?', '63', '67', '73', '63');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est l’adverbe dans la phrase : ''Il court vite'' ?', 'Il', 'Court', 'Vite', 'Vite');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quelle planète est la plus proche du Soleil ?', 'Terre', 'Mars', 'Mercure', 'Mercure');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Comment s’appelle le cri du chien ?', 'Miauler', 'Aboyer', 'Bêler', 'Aboyer');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Combien y a-t-il de zéros dans cent mille ?', '4', '5', '6', '5');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est le pluriel de ''cheval'' ?', 'Chevals', 'Chevaux', 'Chevalles', 'Chevaux');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est le résultat de 45 divisé par 5 ?', '8', '9', '10', '9');");
            db.execSQL("INSERT INTO question (texteQuestion, reponse1, reponse2, reponse3, bonneReponse) VALUES ('Quel est le contraire de ''heureux'' ?', 'Triste', 'Content', 'Joyeux', 'Triste');");
        }
    };
}

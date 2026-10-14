package cl.chopan.churrosnuevos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Clase auxiliar para crear y manejar la base de datos
public class DBHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String NOMBRE_BD = "usuarios.db";
    // Versión (si cambias la estructura, sube este número)
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    // Se ejecuta la primera vez que se crea la BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT UNIQUE, " +
                "contrasena TEXT)");
    }

    // Se ejecuta cuando se actualiza la versión de la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
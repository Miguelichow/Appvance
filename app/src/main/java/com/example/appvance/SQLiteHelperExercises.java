package com.example.appvance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Crea la BD de tipo Exercise
 * @author Miguel Prieto Horcajo
 */
public class SQLiteHelperExercises extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Exercises";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_EXERCISES = "exercisesTable";
    public static final String KEY_DATE = "date";
    public static final String KEY_NAME = "nombre";
    public static final String KEY_SETS = "sets";
    public static final String KEY_REPS = "reps";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
    String strDate = sdf.format(new Date());

    SQLiteDatabase db;
    String query;

    /**
     * Query para crear la tabla
     */
    public static final String SQL_TABLE_EXERCISES = " CREATE TABLE " + TABLE_EXERCISES
            + " ( "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + KEY_NAME + " TEXT PRIMARY KEY, "
            + KEY_SETS + " TEXT, "
            + KEY_REPS + " TEXT"
            + " ) ";

    public SQLiteHelperExercises(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la tabla en la BD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_EXERCISES);
    }

    /**
     * Actualiza la tabla en la BD
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        sqLiteDatabase.execSQL(SQL_TABLE_EXERCISES);
    }

    /**
     * Añade los ejercicios a la tabla
     * @param ex Ejercicio a añadir
     */
    public void addExercise(Exercise ex) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_DATE, strDate);
        values.put(KEY_NAME, ex.nombre);
        values.put(KEY_SETS, ex.sets);
        values.put(KEY_REPS, ex.reps);

        long todo_id = db.insert(TABLE_EXERCISES, null, values);
    }

    /**
     * Obtiene todos los registros de la tabla
     * @return Ejercicios de la tabla
     */
    public Cursor getExercises() {
        db = this.getWritableDatabase();
        query = "SELECT * FROM " + TABLE_EXERCISES;
        return db.rawQuery(query, null);
    }

    /**
     * Borrar los registros de la tabla
     * @param id ID del ejercicio
     */
    public void delete(int id) {
        db = this.getWritableDatabase();
        long todo = db.delete(TABLE_EXERCISES, "RowId = ? ", new String[]{String.valueOf(id)});
    }

    /**
     * Comprueba si existe un determinado registro en la tabla
     * @param fecha Fecha actual
     * @param ejercicio Ejercicio
     * @return True o false dependiendo del resultado
     */
    public boolean ifExists(String fecha, String ejercicio) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXERCISES, new String[]{KEY_DATE, KEY_NAME},
                KEY_DATE + " =? AND " + KEY_NAME + " =?", new String[]{fecha, ejercicio},null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}

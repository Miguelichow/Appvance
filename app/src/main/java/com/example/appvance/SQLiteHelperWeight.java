package com.example.appvance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Crea la BD de tipo Weight
 * @author Miguel Prieto Horcajo
 */
public class SQLiteHelperWeight extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Weight";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_WEIGHT = "weightTable";
    public static final String KEY_DATE = "fecha";
    public static final String KEY_WEIGHT = "peso";

    SQLiteDatabase db;

    /**
     * Query para crear la tabla
     */
    public static final String SQL_TABLE_WEIGHT = " CREATE TABLE " + TABLE_WEIGHT
            + " ( " + KEY_DATE + " TEXT, "
            + KEY_WEIGHT + " TEXT)";

    public SQLiteHelperWeight(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la tabla en la BD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_WEIGHT);
    }

    /**
     * Actualiza la tabla de la BD
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_WEIGHT);
    }

    /**
     * Añade los registros a la tabla
     * @param peso Peso
     * @param fecha fecha
     */
    public void addData (String peso, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, fecha);
        values.put(KEY_WEIGHT, peso);

        db.insert(TABLE_WEIGHT, null, values);
    }

    /**
     * Comprueba si existe el registro según su fecha
     * @param fecha Fecha
     * @return True o false dependiendo del resultado
     */
    public boolean ifExists(String fecha) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WEIGHT, new String[]{KEY_DATE, KEY_WEIGHT},
                KEY_DATE + "=?", new String[]{fecha},null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene todos los registros de la tabla
     * @return
     */
    public Cursor getData() {
        db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_WEIGHT,null);
    }
}

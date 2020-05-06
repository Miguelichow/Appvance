package com.example.appvance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Crea la BD  de tipo Measurements
 * @author Miguel Prieto Horcajo
 */
public class SQLiteHelperMeasurements extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Measurements";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_MEASUREMENTS = "measurementsTable";
    public static final String KEY_DATE = "fecha";
    public static final String KEY_HIP = "hip";

    SQLiteDatabase db;

    /**
     * Query para crear la tabla
     */
    public static final String SQL_TABLE_MEASUREMENTS = " CREATE TABLE " + TABLE_MEASUREMENTS
            + " ( " + KEY_DATE + " TEXT, "
            + KEY_HIP + " TEXT)";

    public SQLiteHelperMeasurements(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la tabla en la BD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_MEASUREMENTS);
    }

    /**
     * Actualiza la tabla de la BD
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MEASUREMENTS);
    }

    /**
     * Añade los registros a la tabla
     * @param hip Medida
     * @param fecha Fecha actual
     */
    public void addData (String hip, String fecha) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, fecha);
        values.put(KEY_HIP, hip);

        db.insert(TABLE_MEASUREMENTS, null, values);
    }

    /**
     * Comprueba si existe un determinado registro en la tabla
     * @param fecha Fecha
     * @return True o false dependiendo del resultado
     */
    public boolean ifExists(String fecha) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEASUREMENTS, new String[]{KEY_DATE, KEY_HIP},
                KEY_DATE + "=?", new String[]{fecha},null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene todos los registros de la tabla
     * @return Medidas de la tabla
     */
    public Cursor getData() {
        db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MEASUREMENTS,null);
    }
}

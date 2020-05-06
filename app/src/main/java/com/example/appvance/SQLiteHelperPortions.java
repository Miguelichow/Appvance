package com.example.appvance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Crea la BD de tipo Portion
 */
public class SQLiteHelperPortions extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Portions";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PORTIONS = "portionsTable";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_P = "protein";
    public static final String KEY_G = "fat";
    public static final String KEY_C = "carbs";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
    String strDate = sdf.format(new Date());

    ContentValues values;

    SQLiteDatabase db;

    /**
     * Query para crear la tabla
     */
    public static final String SQL_TABLE_PORTIONS = " CREATE TABLE " + TABLE_PORTIONS
            + " ( "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + KEY_TIME + " TEXT, "
            + KEY_P + " INTEGER, "
            + KEY_G + " INTEGER, "
            + KEY_C + " INTEGER"
            + " ) ";

    public SQLiteHelperPortions(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la tabla en la BD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_PORTIONS);
    }

    /**
     * Actualiza la tabla de la BD
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_PORTIONS);
            sqLiteDatabase.execSQL(SQL_TABLE_PORTIONS);
        }
    }

    /**
     * Añade los registros a la tabla
     * @param portion
     */
    public void addPortions(Portion portion) {
        db = this.getWritableDatabase();

        long rows = 0;

        values = new ContentValues();

        values.put(KEY_DATE, strDate);
        values.put(KEY_TIME, portion.time);
        values.put(KEY_P, portion.protein);
        values.put(KEY_G, portion.fat);
        values.put(KEY_C, portion.carbs);

        rows = db.insert(TABLE_PORTIONS, null, values);
    }

    /**
     * Obtiene todos los registros de la talba ordenados por fecha
     * @return
     */
    public Cursor getPortions() {
        db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PORTIONS + " ORDER BY " + KEY_DATE,null);
    }

    /**
     * Borra un determinado registro de la tabla
     * @param id ID del registro a borrar
     */
    public void delete(int id) {
        db = this.getWritableDatabase();
        long todo = db.delete(TABLE_PORTIONS, "RowId = ? ", new String[]{String.valueOf(id)});
    }

    /**
     * Comprueba si existe un determinado registro en la tabla
     * @param fecha Fecha
     * @param comida Comida
     * @return True o false dependiendo del resultado
     */
    public boolean ifExists(String fecha, String comida) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PORTIONS, new String[]{KEY_DATE, KEY_TIME},
                KEY_DATE + " =? AND " + KEY_TIME + " =?", new String[]{fecha, comida},null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}

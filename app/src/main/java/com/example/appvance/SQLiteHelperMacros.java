package com.example.appvance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Crea la BD de tipo Macros
 * @author Miguel Prieto Horcajo
 */
public class SQLiteHelperMacros extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Macros";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_MACROS = "macrosTable";
    public static final String KEY_P = "protein";
    public static final String KEY_G = "fat";
    public static final String KEY_C = "carbs";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
    String strDate = sdf.format(new Date());

    ContentValues values;

    SQLiteDatabase db;
    String query;

    /**
     * Query para crear la tabla
     */
    public static final String SQL_TABLE_MACROS = " CREATE TABLE " + TABLE_MACROS
            + " ( "
            + KEY_P + " INTEGER, "
            + KEY_G + " INTEGER, "
            + KEY_C + " INTEGER"
            + " ) ";

    public SQLiteHelperMacros(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la tabla en la BD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_MACROS);
    }

    /**
     * Actualiza la tabla en la BD
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_MACROS);
            sqLiteDatabase.execSQL(SQL_TABLE_MACROS);
        }
    }

    /**
     * Añade los registros a la tabla
     * @param portion Porción a añadirs
     */
    public void addMacros(Portion portion) {
        db = this.getWritableDatabase();

        long rows = 0;

        values = new ContentValues();

        values.put(KEY_P, portion.protein);
        values.put(KEY_G, portion.fat);
        values.put(KEY_C, portion.carbs);

        rows = db.insert(TABLE_MACROS, null, values);
    }

    /**
     * Obtiene todos los registros de la tabla y los ordena de forma de forma descendente
     * @return Registros de la tabla
     */
    public Cursor getMacros() {
        db = this.getReadableDatabase();
        query = "SELECT * FROM " + TABLE_MACROS + " ORDER BY RowId DESC LIMIT 1";
        return db.rawQuery(query, null);
    }
}

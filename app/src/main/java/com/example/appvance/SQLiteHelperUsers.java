package com.example.appvance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Crea la BD de tipo User
 * @author Miguel Prieto Horcajo
 */
public class SQLiteHelperUsers extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Users";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "usersTable";
    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "nombre";
    public static final String KEY_PASSWORD = "pass";

    /**
     * Query para crear la tabla
     */
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    public SQLiteHelperUsers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la tabla en la BD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    /**
     * Actualiza la tabla de la BD
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    /**
     * Añade los registros a la tabla
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USER_NAME, user.nombre);
        values.put(KEY_PASSWORD, user.pass);

        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    /**
     * Verifica que la contraseña pertenece al usuario
     * @param user Usuario
     * @return Null si la contraseña no pertenece al usuario
     */
    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID, KEY_USER_NAME, KEY_PASSWORD},KEY_USER_NAME + "=?",
                new String[]{user.nombre},null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            if (user.pass.equals(user1.pass)) {
                return user1;
            }
        }
        return null;
    }

    /**
     * Verifica si el nombre de usuario existe
     * @param nombre Nombre a comprobar
     * @return True o false dependiento del resultado
     */
    public boolean isNameExists(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID, KEY_USER_NAME, KEY_PASSWORD},
                KEY_USER_NAME + "=?", new String[]{nombre},null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}

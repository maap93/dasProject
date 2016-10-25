package com.dasfinalapp.ong.models;

/**
 * Created by Miguel Angel Arroyo Puerto.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 *
 * This class contains the structure of the database
 * from the columns to the types of each one.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "ONG";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String CATEGORY = "category";
    public static final String OBJECTIVE = "objective";


    // Database Information
    static final String DB_NAME = "ONG.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + TYPE + " TEXT," + CATEGORY + " TEXT, " + OBJECTIVE + " TEXT" +  ");";


    /**
     *
     * Just the constructor of the class
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     *
     * Execute the creation script of the database.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     *
     * To drop the database in case that theres
     * a new version of it.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

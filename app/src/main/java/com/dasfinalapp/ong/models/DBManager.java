package com.dasfinalapp.ong.models;

/**
 * Created by Miguel Angel Arroyo Puerto.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 *
 * This class is basically the primary
 * operations for the app. It includes
 * all the CRUD operations
 *
 */
public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    /**
     *
     * Creates a connection with the database.
     *
     * @return
     * @throws SQLException
     */
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     *
     * Close the connection with the actual database.
     *
     */
    public void close() {
        dbHelper.close();
    }

    /**
     *
     * Insert With automatic id in the database
     *
     */

    public void insert(String name, String type, String category, String objective) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.TYPE, type);
        contentValue.put(DatabaseHelper.CATEGORY, category);
        contentValue.put(DatabaseHelper.OBJECTIVE, objective);
        System.out.println("Content Value: "+contentValue);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    /**
     *
     * Insert the default information of the app
     *
     * @param _id
     * @param name
     * @param type
     * @param test
     * @param objective
     */
    public void insertWithID(int _id, String name, String type, String test, String objective)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ID, _id);
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.TYPE, type);
        contentValue.put(DatabaseHelper.CATEGORY, test);
        contentValue.put(DatabaseHelper.OBJECTIVE, objective);
        System.out.println("Content Value: "+contentValue);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    /**
     *
     * Fetch all the information available of the database.
     *
     * @return the fetched data
     */
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.TYPE, DatabaseHelper.CATEGORY, DatabaseHelper.OBJECTIVE};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    /**
     *
     * The update operation for the database
     * information.
     *
     * @param _id
     * @param name
     * @param type
     * @param category
     * @param objective
     * @return
     */
    public int update(long _id, String name, String type, String category, String objective) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.TYPE, type);
        contentValues.put(DatabaseHelper.CATEGORY, category);
        contentValues.put(DatabaseHelper.OBJECTIVE, objective);
        System.out.println("Content Values (UPDATE): "+contentValues);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}

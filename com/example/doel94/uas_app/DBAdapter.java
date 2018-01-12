package com.example.doel94.uas_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "mycompany.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CREATE = "create table customers (_id integer primary key autoincrement, "
            + "custname text not null, custaddr text not null, "
            + "custgender text not null, custphone text not null)";
    private static final String TABLE_DROP = "DROP TABLE IF EXISTS customers";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_CUSTNAME = "custname";
    public static final String KEY_CUSTADDR = "custaddr";
    public static final String KEY_CUSTGENDER = "custgender";
    public static final String KEY_CUSTPHONE = "custphone";
    private final Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        dbHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context ctx) {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL(TABLE_DROP);
            onCreate(db);
        }
    }
    public DBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public long insertCustomer(String custName, String custAddr, char custGender, String custPhone) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CUSTNAME, custName);
        initialValues.put(KEY_CUSTADDR, custAddr);
        initialValues.put(KEY_CUSTGENDER, Character.toString(custGender));
        initialValues.put(KEY_CUSTPHONE, custPhone);
        return db.insert("customers", null, initialValues);
    }
    public Cursor getAllCustomers() {
        return db.query("customers", new String[] {
                KEY_ROWID, KEY_CUSTNAME, KEY_CUSTADDR, KEY_CUSTGENDER, KEY_CUSTPHONE
        }, null, null, null, null, KEY_ROWID + " DESC");
    }
        }




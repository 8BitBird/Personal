package com.example.workoutgen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "workoutDB.db";
    private final Context mContext;
    private SQLiteDatabase mDataBase;
    String DB_PATH;

    //Constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
             DB_PATH = context.getApplicationInfo().dataDir + "/databases/" + DB_NAME;
             this.mContext = context;
    }
    // Open the database, so we can query it
    public boolean openDataBase () throws SQLException {
        Log.v("DB_PATH", "openingDB");
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close () {
        if (mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}


package com.example.workoutgen;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.sql.SQLException;

public class DatabaseAdapter {
    protected static final String TAG = "Database_Adapter";
    private final Context mContext;
    private SQLiteDatabase db;
    private DBHelper mDbHelper;

    public DatabaseAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
    }

    public DatabaseAdapter open() throws SQLException {
        if (!mDbHelper.openDataBase())
            Log.i(TAG,"database not opened");
        //mDbHelper.close();
        db = mDbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public Cursor getTestData() {
        String selectStmt = "SELECT * FROM (SELECT * FROM ";
        String query = "SELECT * FROM (" +
                selectStmt + "arms ORDER BY RANDOM() LIMIT 2) UNION " +
                selectStmt + "back ORDER BY RANDOM() LIMIT 1) UNION " +
                selectStmt + "lower WHERE activity_name LIKE '%Squat%' ORDER BY RANDOM() LIMIT 1) UNION " +
                selectStmt + "lower WHERE activity_name NOT LIKE '%Squat%' ORDER BY RANDOM() LIMIT 1) UNION " +
                selectStmt + "pecs ORDER BY RANDOM() LIMIT 1) UNION " +
                selectStmt + "abs ORDER BY RANDOM() LIMIT 2)) ORDER BY RANDOM() LIMIT 8";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Log.i("DATA_ADAPTER", query);
        while (!cursor.isAfterLast()){
           // Log.e(TAG, DatabaseUtils.dumpCurrentRowToString(cursor));
            cursor.moveToNext();
        }
        cursor.moveToFirst();
        return cursor;
    }
}

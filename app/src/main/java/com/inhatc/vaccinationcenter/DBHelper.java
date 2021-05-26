package com.inhatc.vaccinationcenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists userInfo ("
                + "_id integer primary key autoincrement,"
                + "userId text not null,"
                + "password text not null,"
                + "age integer,"
                + "infection integer,"
                + "inoculation integer);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists userInfo";

        db.execSQL(sql);
        onCreate(db);
    }
}
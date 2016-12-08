package rkapoors.attendanceapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KAPOOR's on 08-12-2016.
 */
public class databasehelper extends SQLiteOpenHelper {
    public databasehelper(Context ct, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(ct,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(LoginAdapter.databasecreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+"TEMPLATE");

        onCreate(db);
    }
}

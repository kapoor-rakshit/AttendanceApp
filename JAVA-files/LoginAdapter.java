package rkapoors.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by KAPOOR's on 08-12-2016.
 */
public class LoginAdapter {

    static final String databasename="attendance.db";
    static final int databaseversion=1;
    public static final int namecolumn=1;

    static final String databasecreate="create table "+"attendance"+"("+"DTE text,BRANCH text,CATEGORY text,ROLL text);";
    //create
    public SQLiteDatabase db;

    private final Context ct;

    private databasehelper dbhelper;
    public LoginAdapter(Context _ct)
    {
        ct=_ct;
        dbhelper=new databasehelper(ct,databasename,null,databaseversion);
    }
    public LoginAdapter open() throws SQLiteException
    {
        db=dbhelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }
    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    public void insertentry(String date,String branch,String category,String roll)
    {
        ContentValues newval=new ContentValues();

        newval.put("DTE",date);
        newval.put("BRANCH",branch);
        newval.put("CATEGORY",category);
        newval.put("ROLL",roll);

        db.insert("attendance",null,newval);
    }
   /* public void deleteentry(String user)                    //delete
    {
        String where="USERNAME=?";
        db.delete("login",where,new String[]{user});
    }*/
    public String getroll(String date,String branch,String category)                    //read
    {
        Cursor cr=db.query("attendance",null,"DTE=? and BRANCH=? and CATEGORY=?",new String[]{date,branch,category},null,null,null);
        if(cr.getCount()<1)
        {
            cr.close();
            return "not exist";
        }
        cr.moveToFirst();
        String roll="";
        roll=cr.getString(cr.getColumnIndex("ROLL"));
        cr.close();
        return roll;
    }
    /*public void updateentry(String user,String pass)            //update
    {
        ContentValues updateval=new ContentValues();

        updateval.put("USERNAME",user);
        updateval.put("PASSWORD",pass);

        String where="USERNAME=?";
        db.update("login",updateval,where,new String[]{user});
    }*/
}


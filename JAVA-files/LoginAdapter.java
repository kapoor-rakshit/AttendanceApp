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
    public void insertentry(String dae,String banch,String ctegory,String rll)
    {
        ContentValues newval=new ContentValues();

        newval.put("DTE",dae);
        newval.put("BRANCH",banch);
        newval.put("CATEGORY",ctegory);
        newval.put("ROLL",rll);

        db.insert("attendance",null,newval);
    }
   /* public void deleteentry(String user)                    //delete
    {
        String where="USERNAME=?";
        db.delete("login",where,new String[]{user});
    }*/
    public String getroll(String dae,String banch,String ctegory)                    //read
    {
        Cursor cr=db.query("attendance",null,"DTE=? and BRANCH=? and CATEGORY=?",new String[]{dae,banch,ctegory},null,null,null);
        if(cr.getCount()<1)
        {
            cr.close();
            return "not exist";
        }
        cr.moveToFirst();
        String rol="";
        rol=cr.getString(cr.getColumnIndex("ROLL"));
        cr.close();
        return rol;
    }
    public void updateentry(String dae,String banch,String ctegory,String rll)            //update
    {
        ContentValues updateval=new ContentValues();

        updateval.put("DTE",dae);
        updateval.put("BRANCH",banch);
        updateval.put("CATEGORY",ctegory);
        updateval.put("ROLL",rll);

        String where="DTE=? and BRANCH=? and CATEGORY=?";
        db.update("attendance",updateval,where,new String[]{dae,banch,ctegory});
    }
}


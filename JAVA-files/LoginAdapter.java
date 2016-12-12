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

    static final String databasecreate="create table "+"attendance"+"("+"DTE text,BRANCH text,CATEGORY text,GROP text,YR text,ROLL text);";
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
    public void insertentry(String dae,String banch,String ctegory,String rll,String grp,String yer)
    {
        ContentValues newval=new ContentValues();

        newval.put("DTE",dae);
        newval.put("BRANCH",banch);
        newval.put("CATEGORY",ctegory);
        newval.put("ROLL",rll);
        newval.put("GROP",grp);
        newval.put("YR",yer);

        db.insert("attendance",null,newval);
    }
   public void deleteentry(String banch)                    //delete
    {
        String where="BRANCH=?";
        db.delete("attendance",where,new String[]{banch});
    }
    public String getroll(String dae,String banch,String ctegory,String grp,String yer)                    //read
    {
        Cursor cr=db.query("attendance",null,"DTE=? and BRANCH=? and CATEGORY=? and GROP=? and YR=?",new String[]{dae,banch,ctegory,grp,yer},null,null,null);
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
    public void updateentry(String dae,String banch,String ctegory,String rll,String grp,String yer)            //update
    {
        ContentValues updateval=new ContentValues();

        updateval.put("DTE",dae);
        updateval.put("BRANCH",banch);
        updateval.put("CATEGORY",ctegory);
        updateval.put("ROLL",rll);
        updateval.put("GROP",grp);
        updateval.put("YR",yer);

        String where="DTE=? and BRANCH=? and CATEGORY=? and GROP=? and YR=?";
        db.update("attendance",updateval,where,new String[]{dae,banch,ctegory,grp,yer});
    }
    public int compileentry(String banch,String ctegory,String rll,String grp,String yer)
    {
        int ct=0;
        Cursor cr=db.query("attendance",null,"BRANCH=? and CATEGORY=? and GROP=? and YR=?",new String[]{banch,ctegory,grp,yer},null,null,null);

        cr.moveToFirst();
        while(!cr.isAfterLast())
        {
           String rol="";
            String temp="";
           rol=cr.getString(cr.getColumnIndex("ROLL"));
            int l=rol.length();
            int i;
            for(i=0;i<l;i++)
            {
                if(rol.charAt(i)==' ') {
                    if(temp.equals(rll)) {ct++;break;}
                    temp="";}
                else temp+=rol.charAt(i);
            }
            if(temp.equals(rll)&&i==l) ct++;
            cr.moveToNext();
        }
        cr.close();
        return ct;
    }
    public int getmxofcategory(String banch,String ctegory,String grp,String yer)
    {
        Cursor cr=db.query("attendance",null,"BRANCH=? and CATEGORY=? and GROP=? and YR=?",new String[]{banch,ctegory,grp,yer},null,null,null);
        int val=cr.getCount();
        cr.close();
        return val;
    }
}


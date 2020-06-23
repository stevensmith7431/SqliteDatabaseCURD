package com.example.prasanth.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Sqlitedatabase extends SQLiteOpenHelper {

    SQLiteDatabase sqlitedatabase;

    public Sqlitedatabase(Context context) {

        super(context, "Student", null, 1);
        sqlitedatabase = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Detail(Username text ,Emailid text ,Password text," +
                "MobileNumber text,Age text,Gender text,City text,CONSTRAINT Constraint_name PRIMARY KEY(Emailid))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertStudentDetail(String username,String emailid,String password,String mobilenumber,String age,String gender,String city){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("Emailid",emailid);
        contentValues.put("Password",password);
        contentValues.put("MobileNumber",mobilenumber);
        contentValues.put("Age",age);
        contentValues.put("Gender",gender);
        contentValues.put("City",city);
        long k = sqlitedatabase.insert("Detail",null,contentValues);
        return k;
    }

    public String checkUsers(String s_Emailid, String sPassword) {

        Log.d(TAG,"checkUser is called ");
        Cursor cursor;
        cursor=sqlitedatabase.query("RFIDLogin",null,"Username=?",new String[]{s_Emailid},null,null,null);

        if (cursor != null){
            if (cursor.moveToFirst()){

                String temp=cursor.getString(cursor.getColumnIndex("Password"));
                Log.d(TAG,"Password is "+temp);

                if(sPassword.equals(temp)){
                    return "1";
                }else{
                    return "0";
                }
            }
            cursor.close();
        }
        return "";
    }


    public Cursor CheckLogin(String s_email,String s_password){
        Cursor cursor;
        SQLiteDatabase sqlitedatabase = this.getReadableDatabase();
        cursor = sqlitedatabase.rawQuery("SELECT * FROM Detail where Emailid=? and Password=?",new String[]{s_email,s_password});
        cursor.moveToFirst();
        return cursor;
    }

    public  Cursor validateSignup(String s_Emailid){

        Cursor cursor;
        cursor=sqlitedatabase.rawQuery("select Emailid from Detail where Emailid=?",new String[]{s_Emailid});
        cursor.moveToFirst();
        return cursor;
    }

    public  Cursor getMobilenumber(String s_mobilenumber){

        Cursor cursor;
        cursor=sqlitedatabase.rawQuery("select * from Detail where MobileNumber=?",new String[]{s_mobilenumber});
        cursor.moveToFirst();
        return cursor;
    }

    public void updatePassword(String emailid,String password){

        sqlitedatabase.execSQL("update Detail set Password='"+password+"' where Emailid='"+emailid+"'");
    }
}

package com.example.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME ="users.db";
    private static final String TABLE_NAME ="user_details";
    private static final String ID ="id";
    private static final String EMAIL ="email";
    private static final String USERNAME ="Username";
    private static final String GENDER ="gender";
    private static final String PASSWORD1 ="Password1";
    private static final int VERSION_NUMBER = 3;
    private Context context;

    private static final String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EMAIL+" VARCHAR(255) NOT NULL, "+USERNAME+" VARCHAR(255) NOT NULL,"+GENDER+" VARCHAR(7) NOT NULL,"+PASSWORD1+" VARCHAR(32) NOT NULL);";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS "+TABLE_NAME;



    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();

        }

    }

    //adding Users()
    public long insertData(com.example.loginpage.UsersDetails usersDetails){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL, usersDetails.getEmail());
        contentValues.put(USERNAME, usersDetails.getUserName());
        contentValues.put(GENDER, usersDetails.getGender());
        contentValues.put(PASSWORD1, usersDetails.getPassword1());


        long rowId = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        return rowId;
    }

    //findingPassword

    public Boolean findPassword(String uname, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean result = false;

        if(cursor.getCount() == 0){
            Toast.makeText(context,"No data is found",Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext())
            {
                String username = cursor.getString(2);
                String password = cursor.getString(4);

                if(username.equals(uname) && password.equals(pass)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}

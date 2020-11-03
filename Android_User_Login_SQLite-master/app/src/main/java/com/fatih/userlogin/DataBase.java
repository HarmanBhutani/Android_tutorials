package com.fatih.userlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_ID = "ID";


    public DataBase(@Nullable Context context){
        super(context,"user.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT , " + COLUMN_USER_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(UserModel userModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getUsername());
        cv.put(COLUMN_USER_PASSWORD, userModel.getPassword());

        db.insert(USER_TABLE,null,cv);
    }

    public boolean checkLogin(String name, String password){

        String queryName = "SELECT " +COLUMN_USER_ID+" FROM "+USER_TABLE+" WHERE "+COLUMN_USER_NAME+" = "+"\'"+name+"\'";
        String queryPassword = "SELECT " +COLUMN_USER_ID+" FROM "+USER_TABLE+" WHERE "+COLUMN_USER_PASSWORD+" = "+"\'"+password+"\'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorName = db.rawQuery(queryName,null);
        Cursor cursorPassword = db.rawQuery(queryPassword,null);

        int userName = 0 , userPassword =1;
        while(cursorName.moveToNext()){
            userName = cursorName.getInt(0);
        }
        while (cursorPassword.moveToNext()){
            userPassword = cursorPassword.getInt(0);
        }

        if(userName== userPassword){
            return true;
        }else{
            return false;
        }
    }

    public List<UserModel> showAllUsers(){

        List<UserModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userPassword = cursor.getString(2);

                UserModel newUserModel = new UserModel(userID, userName, userPassword);

                returnList.add(newUserModel);


            } while (cursor.moveToNext());
        }else {

        }
        cursor.close();
        db.close();

        return returnList;

    }

    public void deleteUser (UserModel userModel){

        SQLiteDatabase db = this.getReadableDatabase();
        String quaryString = "DELETE FROM " + USER_TABLE + " WHERE " + COLUMN_USER_ID + " = " + userModel.getId();

        Cursor cursor = db.rawQuery(quaryString,null);



    }
}

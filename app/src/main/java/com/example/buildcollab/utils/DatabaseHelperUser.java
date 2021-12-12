package com.example.buildcollab.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelperUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "buildCollabU";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "user";

    public DatabaseHelperUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String name, String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", name);
        values.put("Description", description);

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public Users getUser(String id) {
        String select_query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            Users users = new Users();
            users.setUserId(cursor.getString(0));
            users.setName(cursor.getString(1));
            users.setDescription(cursor.getString(2));
            return users;
        }
        return null;
    }

    public ArrayList<Users> getUsers() {
        ArrayList<Users> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();
                users.setUserId(cursor.getString(0));
                users.setName(cursor.getString(1));
                users.setDescription(cursor.getString(2));
                arrayList.add(users);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteUser(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    public void updateUser(String name, String des, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Description", des);
        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }
}

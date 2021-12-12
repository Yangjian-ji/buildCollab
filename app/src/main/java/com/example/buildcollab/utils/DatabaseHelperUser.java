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
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, Name TEXT, Email TEXT, Password TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Password", password);

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public Users getUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{id});
        if (cursor.moveToFirst()) {
            return generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return null;
    }

    public Users getUserByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Name = ?", new String[]{name});
        if (cursor.moveToFirst()) {
            Users users = generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return users;
        }
        return null;
    }

    public Users getUserByEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            Users users = generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return users;
        }
        return null;
    }

    public ArrayList<Users> getUsers() {
        ArrayList<Users> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Users users = generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
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

    public void updateUser(String name, String email, String pass, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Password", pass);
        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    private Users generateUser(String id, String name, String email, String password) {
        Users users = new Users();
        users.setUserId(id);
        users.setName(name);
        users.setEmail(email);
        users.setPassword(password);
        return users;
    }
}

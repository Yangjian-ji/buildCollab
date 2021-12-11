package com.example.buildcollab.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "buildCollab";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "projects";

    public DatabaseHelper(Context context) {
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

    public void addProject(String title, String des) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public Project getProject(String id) {
        String select_query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            Project project = new Project();
            project.setProjectId(cursor.getString(0));
            project.setTitle(cursor.getString(1));
            project.setDescription(cursor.getString(2));
            return project;
        }
        return null;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setProjectId(cursor.getString(0));
                project.setTitle(cursor.getString(1));
                project.setDescription(cursor.getString(2));
                arrayList.add(project);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteProject(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    public void updateProject(String title, String des, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }
}

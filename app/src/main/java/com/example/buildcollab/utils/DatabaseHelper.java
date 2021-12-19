package com.example.buildcollab.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "buildCollab";
    public static final int DATABASE_VERSION = 1;
    public static final String GROUPS = "groups";
    public static final String PROJECTS = "projects";
    public static final String USERS = "user";
    public static final String USER_GROUPS = "userGroups";
    public static final String USER_PROJECTS = "userProjects";
    public static final String GROUP_POSTS = "groupPosts";
    public static final String PROJECTS_POSTS = "projectsPosts";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE " + GROUPS + "(ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT, Owner String)";
        db.execSQL(query);
        query = "CREATE TABLE " + PROJECTS + "(ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT, Owner String)";
        db.execSQL(query);
        query = "CREATE TABLE " + USERS + "(ID INTEGER PRIMARY KEY, Name TEXT, Email TEXT, Password TEXT)";
        db.execSQL(query);
        query = "CREATE TABLE " + USER_GROUPS + "(ID INTEGER PRIMARY KEY, userId String, groupId String)";
        db.execSQL(query);
        query = "CREATE TABLE " + USER_PROJECTS + "(ID INTEGER PRIMARY KEY, userId String, projectId String)";
        db.execSQL(query);
        query = "CREATE TABLE " + GROUP_POSTS + "(ID INTEGER PRIMARY KEY, userId String, groupId String, Title TEXT, Message TEXT)";
        db.execSQL(query);
        query = "CREATE TABLE " + PROJECTS_POSTS + "(ID INTEGER PRIMARY KEY, userId String, groupId String, Title TEXT, Message TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GROUPS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + PROJECTS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + USER_GROUPS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + USER_PROJECTS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + GROUP_POSTS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + PROJECTS_POSTS);
        onCreate(db);
    }

    public String addPostGroup(String userId, String groupId, String title, String message) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("groupId", groupId);
        values.put("Title", title);
        values.put("Message", message);
        Long id = sqLiteDatabase.insert(GROUP_POSTS, null, values);
        return String.valueOf(id);
    }

    public void deletePostGroup(String postId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(GROUP_POSTS, " ID=" + postId, null);
    }

    public String addPostProject(String userId, String groupId, String title, String message) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("groupId", groupId);
        values.put("Title", title);
        values.put("Message", message);
        Long id = sqLiteDatabase.insert(PROJECTS_POSTS, null, values);
        return String.valueOf(id);
    }

    public void deletePostProject(String groupId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(PROJECTS_POSTS, " ID=" + groupId, null);
    }

    public ArrayList<GroupPost> getPostsGroup(String groupId) {
        ArrayList<GroupPost> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + GROUP_POSTS + " WHERE groupId=" + groupId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                GroupPost post = new GroupPost();
                post.setPostId(cursor.getString(0));
                post.setUserId(cursor.getString(1));
                post.setGroupId(cursor.getString(2));
                post.setTitle(cursor.getString(3));
                post.setMessage(cursor.getString(4));
                arrayList.add(post);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<ProjectPost> getPostsProject(String projectId) {
        ArrayList<ProjectPost> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + GROUP_POSTS + " WHERE groupId=" + projectId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                ProjectPost post = new ProjectPost();
                post.setPostId(cursor.getString(0));
                post.setUserId(cursor.getString(1));
                post.setProjectId(cursor.getString(2));
                post.setTitle(cursor.getString(3));
                post.setMessage(cursor.getString(4));
                arrayList.add(post);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public String addGroups(String title, String des, String owner) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        values.put("Owner", owner);

        Long id = sqLiteDatabase.insert(GROUPS, null, values);
        return String.valueOf(id);
    }

    public Groups getGroup(String id) {
        String select_query = "SELECT * FROM " + GROUPS + " WHERE ID=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            Groups groups = new Groups();
            groups.setGroupId(cursor.getString(0));
            groups.setTitle(cursor.getString(1));
            groups.setDescription(cursor.getString(2));
            groups.setOwnerId(cursor.getString(3));
            return groups;
        }
        return null;
    }

    public ArrayList<Groups> getGroups() {
        ArrayList<Groups> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + GROUPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Groups groups  = new Groups();
                groups.setGroupId(cursor.getString(0));
                groups.setTitle(cursor.getString(1));
                groups.setDescription(cursor.getString(2));
                groups.setOwnerId(cursor.getString(3));
                arrayList.add(groups);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteGroup(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(GROUPS, "ID=" + ID, null);
        sqLiteDatabase.delete(USER_GROUPS, "groupId=" + ID, null);
    }

    public void updateGroup(String title, String des, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        sqLiteDatabase.update(GROUPS, values, "ID=" + ID, null);
    }


    public String addProject(String title, String des, String owner) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        values.put("Owner", owner);

        Long userId = sqLiteDatabase.insert(PROJECTS, null, values);
        sqLiteDatabase.close();
        return String.valueOf(userId);
    }

    public Project getProject(String id) {
        String select_query = "SELECT * FROM " + PROJECTS + " WHERE ID=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            Project project = new Project();
            project.setProjectId(cursor.getString(0));
            project.setTitle(cursor.getString(1));
            project.setDescription(cursor.getString(2));
            project.setOwnerId(cursor.getString(3));
            return project;
        }
        return null;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + PROJECTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setProjectId(cursor.getString(0));
                project.setTitle(cursor.getString(1));
                project.setDescription(cursor.getString(2));
                project.setOwnerId(cursor.getString(3));
                arrayList.add(project);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<Project> getFiltedProjects(String filter) {
        ArrayList<Project> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + PROJECTS + " WHERE ID=" + filter;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setProjectId(cursor.getString(0));
                project.setTitle(cursor.getString(1));
                project.setDescription(cursor.getString(2));
                project.setOwnerId(cursor.getString(3));
                arrayList.add(project);
            } while (cursor.moveToNext());
        }
        return arrayList;

    }

    public void deleteProject(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(PROJECTS, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    public void updateProject(String title, String des, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        sqLiteDatabase.update(PROJECTS, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }


    public void addUser(String name, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Password", password);

        sqLiteDatabase.insert(USERS, null, values);
        sqLiteDatabase.close();
    }

    public Users getUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS + " WHERE ID = ?", new String[]{id});
        if (cursor.moveToFirst()) {
            return generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return null;
    }

    public Users getUserByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS + " WHERE Name = ?", new String[]{name});
        if (cursor.moveToFirst()) {
            Users users = generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return users;
        }
        return null;
    }

    public Users getUserByEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS + " WHERE Email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            Users users = generateUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return users;
        }
        return null;
    }

    public ArrayList<Users> getUsers() {
        ArrayList<Users> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM " + USERS, null);

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
        sqLiteDatabase.delete(USERS, "ID=" + ID, null);
        sqLiteDatabase.delete(USER_GROUPS, "userId=" + ID, null);
        sqLiteDatabase.close();
    }

    public void updateUser(String name, String email, String pass, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Password", pass);
        sqLiteDatabase.update(USERS, values, "ID=" + ID, null);
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


    public void addUserToGroup(String userId, String groupId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("groupId", groupId);

        sqLiteDatabase.insert(USER_GROUPS, null, values);
    }

    public void removeUserGroup(String userId, String groupId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(USER_GROUPS, "userId=" + userId + " and groupId=" + groupId, null);
    }

    public List<String> getGroups(String id) {
        String select_query = "SELECT * FROM " + USER_GROUPS + " WHERE userId=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        List<String> groups = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                groups.add(cursor.getString(2));
            }
            while (cursor.moveToNext());
        }
        return groups;
    }

    public List<Groups> getGroupsBelong(String id) {
        String select_query = "SELECT u1.ID, u1.Title, u1.Description from " + GROUPS + " as u1, " + USER_GROUPS + " as u2 where u1.id=u2.groupId and u2.userId=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        List<Groups> groups = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                Groups group = new Groups();
                group.setGroupId(cursor.getString(0));
                group.setTitle(cursor.getString(1));
                group.setDescription(cursor.getString(2));
                groups.add(group);
            }
            while (cursor.moveToNext());
        }
        return groups;
    }

    public List<Groups> getGroupsNotBelong(String id) {
        String select_query = "SELECT u1.ID, u1.Title, u1.Description from " + GROUPS + " as u1, " + USER_GROUPS + " as u2 where u1.id!=u2.id and u2.userId=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        List<Groups> groups = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                Groups group = new Groups();
                group.setGroupId(cursor.getString(0));
                group.setTitle(cursor.getString(1));
                group.setDescription(cursor.getString(2));
                groups.add(group);
            }
            while (cursor.moveToNext());
        }
        return groups;
    }

    public boolean isUserInGroup(String userId, String groupId) {
        String select_query = "SELECT ID from " + USER_GROUPS + "  where userId=" + userId + " and groupId=" + groupId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    public boolean isUserInProject(String userId, String projectId) {
        String select_query = "SELECT ID from " + USER_PROJECTS + "  where userId=" + userId + " and projectId=" + projectId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.getCount() > 0)
            return true;
        return false;
    }


    public List<String> getUserGroups(String id) {
        String select_query = "SELECT * FROM " + USER_GROUPS + " WHERE groupId=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        List<String> groups = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                groups.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        return groups;
    }


    public void addUserToProject(String userId, String projectId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("projectId", projectId);

        sqLiteDatabase.insert(USER_PROJECTS, null, values);
    }

    public void removeUserProject(String userId, String groupId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(USER_PROJECTS, "userId=" + userId + " and  groupId=" + groupId, null);
    }


    public List<Project> getProjects(String id) {
        String select_query = "SELECT u1.ID, u1.Title, u1.Description from " + PROJECTS + " as u1, " + USER_PROJECTS + " as u2 where u1.id=u2.id and u2.userId=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        List<Project> projects = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setProjectId(cursor.getString(0));
                project.setTitle(cursor.getString(1));
                project.setDescription(cursor.getString(2));
                projects.add(project);
            }
            while (cursor.moveToNext());
        }
        return projects;
    }


}

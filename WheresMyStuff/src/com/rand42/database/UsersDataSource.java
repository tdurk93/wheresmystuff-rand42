package com.rand42.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rand42.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 *Class that interfaces with the database for users
 * @author Rand-42
 */
public class UsersDataSource implements IUsersDataSource
{
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD
    , MySQLiteHelper.COLUMN_ENABLED, MySQLiteHelper.COLUMN_ADMIN};


    public UsersDataSource(Context context)
    {
       dbHelper = new MySQLiteHelper(context);
    }

    @Override
    public void open() throws SQLiteException
    {
        database = dbHelper.getWritableDatabase();
        if(!database.isReadOnly())
        {
            database.execSQL("pragma foreign_keys = on;");
        }
    }
    @Override
    public void close()
    {
        dbHelper.close();
    }


    @Override
    public boolean createUser(String name, String email, String password, boolean isAdmin)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_EMAIL, email);
        values.put(MySQLiteHelper.COLUMN_PASSWORD, password);
        values.put(MySQLiteHelper.COLUMN_ADMIN, isAdmin);
        values.put(MySQLiteHelper.COLUMN_ENABLED,1);

        long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null,values);
        return insertId !=-1;



    }
    @Override
    public User getUserById(long id)
    {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, allColumns, MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""}, null,null,null);
        return cursorToUser(cursor);
    }
    @Override
    public User getUserByEmail(String email)
    {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, allColumns, MySQLiteHelper.COLUMN_EMAIL+"=?", new String[]{email}, null,null,null);
        return cursorToUser(cursor);
    }

    @Override
    public User loginUser(String email, String password)
    {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,  allColumns,  "email=? and password=?", new String[]{email,password}, null,null,null);
        if(cursor.isAfterLast())
            return null;
        return cursorToUser(cursor);
    }
    @Override
    public boolean deleteUser(long id)
    {
        int rowsAffected = database.delete(MySQLiteHelper.TABLE_USERS,  MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""});
        return rowsAffected !=0;
    }
    @Override
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<User>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, allColumns, null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    /**
     * Makes a user from a database cursor
     * @param cursor The database cursor
     * @return The created user
     */
    public static User cursorToUser(Cursor cursor)
    {
        if(cursor.isAfterLast())
            return null;
        if(cursor.isBeforeFirst())
                cursor.moveToNext();

        User user = new User(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_PASSWORD)),
                cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ADMIN))==1,
                cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ENABLED))==1);

        return user;
    }

    @Override
    public void lockUser(long id)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ENABLED, 0);
        database.update(MySQLiteHelper.TABLE_USERS, values, MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""} );
    }
    @Override
    public void unlockUser(long id)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ENABLED, 1);
        database.update(MySQLiteHelper.TABLE_USERS, values, MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""} );
    }
}

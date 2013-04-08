package com.rand42.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database class for SQLite3
 * @author Rand-42
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "rand42.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ITEMS="items";
    public static final String TABLE_USERS="users";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_DESC="desc";
    public static final String COLUMN_USER="user";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_ADMIN="admin";
    public static final String COLUMN_ENABLED="enabled";
    public static final String COLUMN_LOST = "lost";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_CATEGORY="category";

    private static final String DATABASE_MAKE = "create table "+TABLE_ITEMS+"("+COLUMN_ID
            +" integer primary key autoincrement, "+COLUMN_NAME+" text not null, "+COLUMN_DESC+" text not null, "+COLUMN_CATEGORY+" text not null, "+COLUMN_LOST+" integer not null, "+COLUMN_DATE+" integer not null, "+COLUMN_USER+" integer not null, " +
            "foreign key("+COLUMN_USER+") references "+TABLE_USERS+"("+COLUMN_ID+") on delete cascade);";

    private static final String USERS_MAKE =  "create table "+TABLE_USERS+"("+COLUMN_ID
            +" integer primary key autoincrement, "+COLUMN_EMAIL+" text not null unique, "+COLUMN_NAME+" text not null, "+COLUMN_PASSWORD+" text not null, "+
            COLUMN_ADMIN+" integer, "+COLUMN_ENABLED+" integer);";





    /**
     * Constructs MySQLiteHelper
     * @param context application context
     */
    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    /**
     * Creates database
     * @param sqLiteDatabase is database IO object
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DATABASE_MAKE);
        sqLiteDatabase.execSQL(USERS_MAKE);
    }

    /**
     * Recreates database for certain database modifications
     * @param sqLiteDatabase is database IO object
     * @param i not used
     * @param i2 not used
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_ITEMS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(sqLiteDatabase);
    }
}

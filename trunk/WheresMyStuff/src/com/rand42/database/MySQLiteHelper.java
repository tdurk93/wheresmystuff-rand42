package com.rand42.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/15/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
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

    private static final String DATABASE_MAKE = "create table "+TABLE_ITEMS+"("+COLUMN_ID
            +" integer primary key autoincrement, "+COLUMN_DESC+" text not null, "+COLUMN_LOST+" integer not null, "+COLUMN_USER+" integer not null, " +
            "foreign key("+COLUMN_USER+") references "+TABLE_USERS+"("+COLUMN_ID+") on delete cascade);"+
            "create table "+TABLE_USERS+"("+COLUMN_ID
            +" integer primary key autoincrement, "+COLUMN_EMAIL+" text not null unique, "+COLUMN_NAME+" text not null, "+COLUMN_PASSWORD+" text not null, "+
            COLUMN_ADMIN+" integer, "+COLUMN_ENABLED+" integer);";






    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DATABASE_MAKE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(sqLiteDatabase);
    }
}

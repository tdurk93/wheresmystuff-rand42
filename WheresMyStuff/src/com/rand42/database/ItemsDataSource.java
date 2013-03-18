package com.rand42.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rand42.model.Item;
import com.rand42.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/15/13
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemsDataSource
{
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_DESC
            , MySQLiteHelper.COLUMN_USER, MySQLiteHelper.COLUMN_LOST};


    public ItemsDataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLiteException
    {
        database = dbHelper.getWritableDatabase();
    }
    public void close()
    {
        dbHelper.close();
    }

    public void createItem(String name, String description, User owner, boolean lost)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_DESC, description);
        values.put(MySQLiteHelper.COLUMN_USER, owner.getID());
        values.put(MySQLiteHelper.COLUMN_LOST, lost?1:0);

        long insertId = database.insert(MySQLiteHelper.TABLE_ITEMS, null,values);



    }
    public void deleteItem(long id)
    {
        int rowsAffected = database.delete(MySQLiteHelper.TABLE_ITEMS,  MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""});
    }
    public List<Item> getAllUserItems(User user)
    {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ITEMS, allColumns, MySQLiteHelper.COLUMN_USER+"=?",new String[]{user.getID()+""},null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    /**
     * Makes a user from a database cursor
     * @param cursor The database cursor
     *
     * @return The created user
     */
    private Item cursorToItem(Cursor cursor)
    {
        int userId = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_USER));
        Cursor userCursor = database.query(MySQLiteHelper.TABLE_USERS,  new String[]{MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD
                , MySQLiteHelper.COLUMN_ENABLED, MySQLiteHelper.COLUMN_ADMIN}, MySQLiteHelper.COLUMN_ID+"=?", new String[]{userId+""}, null,null,null);

        userCursor.moveToFirst();
        User user = new User(userCursor.getString(userCursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME)),
                userCursor.getString(userCursor.getColumnIndex(MySQLiteHelper.COLUMN_EMAIL)),
                userCursor.getString(userCursor.getColumnIndex(MySQLiteHelper.COLUMN_PASSWORD)),
                userCursor.getInt(userCursor.getColumnIndex(MySQLiteHelper.COLUMN_ADMIN))==1,
                userCursor.getInt(userCursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));

        Item item = new Item(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME)), cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_DESC)), user, cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)),(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_LOST))==1)?true:false);
        return item;


    }
}

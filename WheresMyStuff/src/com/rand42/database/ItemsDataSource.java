package com.rand42.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rand42.model.Item;
import com.rand42.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/15/13
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemsDataSource implements IItemsDataSource
{
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_DESCRIPTION
            , MySQLiteHelper.COLUMN_USER, MySQLiteHelper.COLUMN_DATE, MySQLiteHelper.COLUMN_LOST, MySQLiteHelper.COLUMN_CATEGORY};


    public ItemsDataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }

    @Override
    public void open() throws SQLiteException
    {
        database = dbHelper.getWritableDatabase();
    }
    @Override
    public void close()
    {
        dbHelper.close();
    }

    @Override
    public void createItem(String name, String description, User owner, Date date, boolean lost, String category)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_USER, owner.getID());
        values.put(MySQLiteHelper.COLUMN_LOST, lost?1:0);
        values.put(MySQLiteHelper.COLUMN_DATE, date.getTime());
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category);

        database.insert(MySQLiteHelper.TABLE_ITEMS, null,values);



    }
    @Override
    public boolean deleteItem(long id)
    {
        int rowsAffected = database.delete(MySQLiteHelper.TABLE_ITEMS,  MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""});
        return rowsAffected != 0;
    }
    @Override
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
    @Override
    public Item getItemById(long id)
    {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ITEMS, allColumns, MySQLiteHelper.COLUMN_ID+"=?", new String[]{id+""},null,null,null);
        return cursorToItem(cursor);
    }

    private Item cursorToItem(Cursor cursor)
    {
        if(cursor.isAfterLast())
            return null;
        if(cursor.isBeforeFirst())
            cursor.moveToFirst();

        int userId = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_USER));
        Cursor userCursor = database.query(MySQLiteHelper.TABLE_USERS,  new String[]{MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD
                , MySQLiteHelper.COLUMN_ENABLED, MySQLiteHelper.COLUMN_ADMIN}, MySQLiteHelper.COLUMN_ID+"=?", new String[]{userId+""}, null,null,null);

        userCursor.moveToFirst();
        User user = UsersDataSource.cursorToUser(userCursor);
        userCursor.close();

        Date date = new Date(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_DATE)));
        Item item = new Item(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME)), cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_DESCRIPTION)),
                user,
                date,
                cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)),
                (cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_LOST))==1), cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CATEGORY)));

        return item;


    }

    @Override
    public List<Item> search(String query)
    {
        Cursor cursor = database.rawQuery("select * from "+MySQLiteHelper.TABLE_ITEMS+" where "+MySQLiteHelper.COLUMN_NAME+" like '%"+query+"%' or "+MySQLiteHelper.COLUMN_CATEGORY+" like '%"+query+"%' ",null);
        cursor.moveToFirst();
        List<Item> items = new ArrayList<Item>();
        while(!cursor.isAfterLast())
        {
            items.add(cursorToItem(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }
}

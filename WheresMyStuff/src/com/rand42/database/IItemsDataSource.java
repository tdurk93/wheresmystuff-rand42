package com.rand42.database;

import android.database.sqlite.SQLiteException;
import com.rand42.model.Item;
import com.rand42.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/3/13
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IItemsDataSource
{
    void open() throws SQLiteException;

    void close();

    void createItem(String name, String description, User owner, Date date, boolean lost, String category);

    boolean deleteItem(long id);

    List<Item> getAllUserItems(User user);

    Item getItemById(long id);

    List<Item> search(String query);
}

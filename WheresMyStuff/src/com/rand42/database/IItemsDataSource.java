package com.rand42.database;

import android.database.sqlite.SQLiteException;
import com.rand42.model.Item;
import com.rand42.model.User;

import java.util.Date;
import java.util.List;

/**
 * Class that interfaces with the database for items
 * @author Rand-42
 */
public interface IItemsDataSource
{
    /**
     * Opens the datasource. Must be called before any other operation
     * @throws SQLiteException
     */
    void open() throws SQLiteException;

    /**
     * Closes the datasource. Should be called after read/write operations have completed
     */
    void close();

    /**
     * Creates a new item in the database
     * @param name Name of the item
     * @param description Description of the item
     * @param owner The User associated with items
     * @param date Date object
     * @param lost Lost status
     * @param category Category string
     */
    void createItem(String name, String description, User owner, Date date, boolean lost, String category);

    /**
     * Deletes an item by id
     * @param id The id param of the item
     * @return
     */
    boolean deleteItem(long id);

    /**
     * Gets all items associated with a user
     * @param user The associated user
     * @return
     */
    List<Item> getAllUserItems(User user);

    /**
     * Gets an item by its id param
     * @param id The id of the item
     * @return The item
     */
    Item getItemById(long id);

    /**
     * Searches categories and names for items
     * @param query The search query
     * @return Results
     */
    List<Item> search(String query);
}

package com.rand42.database;

import android.database.sqlite.SQLiteException;
import com.rand42.model.User;

import java.util.List;

/**
 * Class that interfaces with the database for items
 * @author Rand-42
 */
public interface IUsersDataSource
{
    /**
     * Opens the datasource. Must be called before any other operation
     * @throws SQLiteException
     */
    void open() throws SQLiteException;

    /**
     * Closes the datasource. Should be called after operations are finished
     */
    void close();

    /**
     * Creates a new user
     * @param name Name
     * @param email Email. UNIQUE
     * @param password Password
     * @param isAdmin Admin status
     * @return Success if the user was created or not
     */
    boolean createUser(String name, String email, String password, boolean isAdmin);

    /**
     * Gets a user by an id
     * @param id The id param of the user
     * @return  The User
     */
    User getUserById(long id);

    /**
     * Gets a user by email address
     * @param email The email address
     * @return The User
     */
    User getUserByEmail(String email);

    /**
     * Checks credentials, returns a user or null
     * @param email Email
     * @param password Password
     * @return User if credentials match, null otherwise
     */
    User loginUser(String email, String password);

    /**
     * Deletes a user by id
     * @param id The id param
     * @return Success or non success
     */
    boolean deleteUser(long id);

    /**
     * Gets all the users from the database
     * @return A list of all the users
     */
    List<User> getAllUsers();

    /**
     * Sets a user's lock status to true.
     * @param id The id param of the user
     */
    void lockUser(long id);

    /**
     * Sets a user's lock status to false
     * @param id The id param of the user
     */
    void unlockUser(long id);
}

package com.rand42.model;

import com.parse.LogInCallback;
import com.parse.SignUpCallback;

import java.util.Collection;
import java.util.List;

/**
 * 
 * All Models are one and they are sacred.
 * 
 * @author Stefano
 *
 */
public interface IModel
{

	/**
	 * In this manner, the man of given name gave up
	 * his word that he might pass and have his name
	 * writ in the log of the Inn
	 * 
	 *
     * @param name
     * @param password
     * @return
	 */
	void logIn(String name, String password, LogInCallback callback);
	
	/**
	 * In this manner, the uninitiated man of given
	 * name might be enlightened.
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @return
	 */
	void addUser(String email, String name, String password, SignUpCallback callback);
	
	/**
	 * In this manner is the man struck from the
	 * Log of the Inn
	 */
	void logOut();
	
	/**
	 * In this manner is the man of the Log returned.
	 * @return
	 */
	User getUser();

    /**
     * Sets the curerntUser
     * @param user User
     */
    void setUser(User user);

    /**
     * Checks to see if user has logged in x amount of times (def 3)
     * @param string email address to check
     * @return true if under 3
     */
    boolean checkUserAttempts(String string);

    /**
     * Creates a new Item
     * @param name
     * @param description
     * @param owner
     */
    void createItem(String name, String description, User owner, boolean lost);

    /**
     * Returns the items associated with a user
     * @param user User
     * @param requestor The object that requested the data
     * @return Items
     */
    void getUserItems(User user, final Requestor<Item> requestor);

    /**
     * Gets an item with a UID
     * @param uid uid
     * @return item
     */
    Item getItem(String uid);

    void deleteItem(Item item, Requestor<Item> requestor);
}

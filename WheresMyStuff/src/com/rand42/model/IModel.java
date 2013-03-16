package com.rand42.model;

import com.parse.SignUpCallback;

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
     *
     *
     * @param email
     * @param password
     * @return
	 */
	boolean logIn(String email, String password);
	
	/**
	 * In this manner, the uninitiated man of given
	 * name might be enlightened.
	 * 
	 *
     * @param email
     * @param name
     * @param password
     * @return
	 */
	void addUser(String email, String name, String password, boolean isAdmin);
	
	/**
	 * In this manner is the man struck from the
	 * Log of the Inn
	 */
	void logOut();
	
	/**
	 * In this manner is the man of the Log returned.
	 * @return
	 */
	User getCurrentUser();
    List<User> getAllUsers();

    /**
     * Sets the curerntUser
     * @param user User
     */
    void setCurrentUser(User user);

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
     *
     * @param user User
     * @return Items
     */
    List<Item> getUserItems(User user);

    /**
     * Gets an item with a UID
     *
     * @param id@return item
     */
    Item getItemById(long id);

    void deleteItem(Item item);

    void resetAttempts(String email);

    User getUser(String email);

    void lockUser(User currentUser);
    void unlockUser(User currentUser);

    boolean isUserLocked(User user);

    void queueUserDelete(User user);
    void performUserDelete(User user);

    boolean isUserQueued(User user);

}

package com.rand42.model;

import java.util.Date;
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
	boolean addUser(String email, String name, String password, boolean isAdmin);
	
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
	
	/**
	 * Find me a list of all men.
	 * @return
	 */
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
     * @param date
     */
    void createItem(String name, String description, User owner, Date date, boolean lost, String category);

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
     * @param id
     * @return item
     */
    Item getItemById(long id);

    /**
     * The item shall be cast into the void.
     * @param item
     * @return
     */
    boolean deleteItem(Item item);

    /**
     * The user has been pardoned his errors.
     * @param email
     */
    void resetAttempts(String email);

    /**
     * Accessor for a user,
     * Simply pass in the email.
     * @param email
     * @return
     */
    User getUser(String email);
    
    /**
     * Accessor for a user,
     * Simply pass in the ID#
     * @param id
     * @return
     */
    User getUser(long id);

    /**
     * Prevent a user from logging in.
     * @param currentUser
     */
    void lockUser(User currentUser);
    
    /**
     * Allow a user to log in.
     * @param currentUser
     */
    void unlockUser(User currentUser);
    
    /**
     * Enable the given filter.
     * @param filter
     */
    void setFilter(ItemFilter filter);
    
    /**
     * Accessor for the chosen filter.
     * @return
     */
    ItemFilter getFilter();
    
    /**
     *	Permanently remove a user from the system 
     * @param user
     * @return
     */
    boolean deleteUser(User user);

    /**
     * Apply a query.
     * @param query
     * @return
     */
    List<Item> searchItems(String query);

}

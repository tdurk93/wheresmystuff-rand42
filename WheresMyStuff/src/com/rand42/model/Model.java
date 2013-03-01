package com.rand42.model;

/**
 * 
 * All Models are one and they are sacred.
 * 
 * @author Stefano
 *
 */
public interface Model {

	/**
	 * In this manner, the man of given name gave up
	 * his word that he might pass and have his name
	 * writ in the log of the Inn
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	boolean logIn(String name, String password);
	
	/**
	 * In this manner, the uninitiated man of given
	 * name might be enlightened.
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @return
	 */
	boolean addUser(String email, String name, String password);
	
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
	
}

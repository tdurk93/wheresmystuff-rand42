package com.rand42.model;

import com.parse.*;

/**
 * 
 * Definition of a default user in the system.
 * This class wraps ParseUser to decouple our system from Parse.
 * @author Stefano
 *
 */

public class User {

	private ParseUser user;
	private String name;
	private String id;
	private String email;
	
	public User(ParseUser target){
		if(target == null){
			name = "GeorgeBurdell";
			id = "none";
			email= "gBurdell@gatech.edu";
		}
		
		user =target;
		name = target.getUsername();
		id = target.getObjectId();
		email = target.getEmail();
	}
	
	/**
	 * Selfexplanatory
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Selfexplanatory
	 * @return
	 */
	public String getID(){
		return id;
	}
	
	/**
	 * Selfexplanatory
	 * @return
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Selfexplanatory
	 */
	public String toString(){
		return name;
	}
	
	/**
	 * Selfexplanatory
	 */
	public void logOut(){
		user.logOut();
	}
	
}

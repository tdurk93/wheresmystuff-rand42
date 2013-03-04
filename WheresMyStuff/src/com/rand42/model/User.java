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

            target.fetchIfNeededInBackground(new GetCallback() //some targets may be skeletons without data. must load here
            {
                @Override
                public void done(ParseObject parseObject, ParseException e)
                {
                    ParseUser parseUser = (ParseUser)parseObject;
                    user =parseUser;
                    name = parseUser.getUsername();
                    id = parseUser.getObjectId();
                    email = parseUser.getEmail();
                }
            });
	}
	
	/**
	 * Selfexplanatory
	 * @return
	 */
	public String getName(){
		return name;
	}
    public ParseUser getParseUser()
    {
        return user;
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

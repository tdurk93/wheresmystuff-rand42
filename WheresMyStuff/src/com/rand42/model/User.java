package com.rand42.model;

import android.util.Log;
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
    private boolean isAdmin;
    private boolean active;

    public User(ParseUser target){

            if(!target.isDataAvailable())
            target.fetchIfNeededInBackground(new GetCallback() //some targets may be skeletons without data. must load here
            {
                @Override
                public void done(ParseObject parseObject, ParseException e)
                {
                    ParseUser parseUser = (ParseUser)parseObject;
                    user =parseUser;
                    name = parseUser.getString("name");
                    id = parseUser.getObjectId();
                    email = parseUser.getUsername();
                    isAdmin = parseUser.getBoolean("admin");
                }
            });
        else
            {
                user = target;
                name = target.getString("name");
                id = target.getObjectId();
                email = target.getUsername();
                isAdmin = target.getBoolean("admin");
                active=true;
                if(SecurityManager.getSecurityManager().isUserQueued(this))
                    active=false;


            }


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
		return email;
	}
    public boolean isAdmin()
    {
        return isAdmin;
    }

	
	/**
	 * Selfexplanatory
	 */
	public void logOut(){
		user.logOut();
	}
    public void setInactive()
    {
        active=false;
    }


    public boolean isActive()
    {
        return active;
    }
}

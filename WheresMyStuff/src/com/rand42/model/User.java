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

	private String name;
	private long id;
	private String email;
    private boolean isAdmin;
    private boolean active;
    private String password;


    public User(String name, String email, String password, boolean admin,  long id )
    {
        isAdmin = admin;
        this.email = email;
        this.password = password;
        this.id = id;
        this.name = name;
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
	public long getID(){
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

    public void setInactive()
    {
        active=false;
    }


    public boolean isActive()
    {
        return active;
    }
}
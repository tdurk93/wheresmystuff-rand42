package com.rand42.model;

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
    private String category;


    public User(String name, String email, String password, boolean admin,  long id, boolean active )
    {
        isAdmin = admin;
        this.email = email;
        this.password = password;
        this.id = id;
        this.name = name;
        this.active = active;
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

    public void setActive(boolean activity)
    {
        active=activity;
    }


    public boolean isActive()
    {
        return active;
    }
}

package com.rand42.model;


import java.util.HashMap;

/**
 * 
 * Manages user login count over a given period of time.
 * 
 * @author Stefano
 *
 */
public class SecurityManager {

	private HashMap<String, Integer> lockouts = new HashMap<String,Integer>();
	private static SecurityManager defaultInstance;
	private final int LOCK = 3; 
	
	/**
	 * Can't be having this class instantiated anywhere else.
	 */
	private SecurityManager(){}
	
	/**
	 * It's a singleton so yeah
	 * @return
	 */
	public static SecurityManager getSecurityManager(){
		if(defaultInstance==null)
			defaultInstance = new SecurityManager();
		return defaultInstance;
	}
	
	/**
	 * Checking a user adds the user if one is not
	 * currently being watched.
	 * After the third login attempt the user
	 * is locked out.
	 * 
	 * @param username email
	 * @return
	 */
	public boolean check(String username){
		if(!lockouts.containsKey(username))
			lockouts.put(username, 0);
		lockouts.put(username,lockouts.get(username)+1);
		return lockouts.get(username) < LOCK;
	}
	
	/**
	 * Resets a user's login attempt count.
	 * @param username
	 */
    public void reset(String username)
    {
        lockouts.put(username,0);
    }
	
	/**
	 * Deletes all logs of user activity
	 */
	public void reset()
    {
		lockouts = new HashMap<String,Integer>();
	}

}

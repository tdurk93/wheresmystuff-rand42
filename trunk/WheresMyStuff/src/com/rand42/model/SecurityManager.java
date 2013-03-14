package com.rand42.model;
import com.parse.ParseObject;

import java.util.*;

/**
 * 
 * Manages user login count over a given period of time.
 * 
 * @author Stefano
 *
 */
public class SecurityManager {

	//TODO: Lockout users based on locked status
	
	private HashMap<String, Integer> lockouts = new HashMap<String,Integer>();
    private HashMap<String, ParseObject> lockedUsers = new HashMap<String, ParseObject>();
    private HashMap<String, ParseObject> queuedDelete = new HashMap<String, ParseObject>();
	private static SecurityManager defaultInstance;
	private final int LOCK = 3; //three attempts at login
	
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
	 * @param user
	 * @return
	 */
	public boolean check(String username){
		if(!lockouts.containsKey(username))
			lockouts.put(username, 0);
		lockouts.put(username,lockouts.get(username)+1);
		return lockouts.get(username) < LOCK;
	}
    public void reset(String username)
    {
        lockouts.put(username,0);
    }
	
	/**
	 * Deletes logs of user activity
	 */
	public void reset()
    {
		lockouts = new HashMap<String,Integer>();
	}

    public void lockUser(ParseObject lockedUser)
    {
       lockedUsers.put(lockedUser.getString("username"), lockedUser);
    }
    public ParseObject unlockUser(String email)
    {
        return lockedUsers.remove(email);
    }
    public void queueDelete(ParseObject queuedUser)
    {
        queuedDelete.put(queuedUser.getString("username"), queuedUser);
    }
    public ParseObject performDelete(String email)
    {
        return queuedDelete.remove(email);
    }

    public boolean isUserLocked(User user)
    {
        return lockedUsers.containsKey(user.getEmail());
    }
    public boolean isUserQueued(User user)
    {
        return queuedDelete.containsKey(user.getEmail());
    }
}

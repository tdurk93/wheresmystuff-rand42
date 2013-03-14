package com.rand42.model;

import android.app.Activity;
import android.util.Log;

import com.parse.*;
import com.rand42.views.WheresMyStuffApplication;

/**
 * The DatabaseHandler is a singleton with Parse.com database read and write methods
 * @author Rand-42
 *
 */
public class DatabaseHandler 
{
	private static DatabaseHandler handler;



	
	private DatabaseHandler()
    {

    }
	/**
	 * Gets the Database Handler
	 * @return The database handler
	 */
	public static DatabaseHandler getHandler()
	{
		if(handler==null)
			handler = new DatabaseHandler();
		return handler;
	}


    /**
     * Logs in on parse
     * @param email  email
     * @param password password
     * @param callback  callback method to call when operation completes
     */
	public void login(String email, String password, LogInCallback callback)
	{
		ParseUser.logInInBackground(email,password, callback);
	}

    /**
     * Creates a new user on parse
     * @param email email
     * @param name name
     * @param password password
     * @param callback method to call when operation finishes
     */
	public void createUser(String email, String name, String password, boolean isAdmin, SignUpCallback callback)
	{
		ParseUser user = new ParseUser();
		user.setUsername(email);
		user.setPassword(password);
		user.put("name", name);
        user.put("admin", isAdmin);
		user.signUpInBackground(callback);

	}


    /**
     * Retrieves the items associated with a user
     * @param user
     * @param callback Method to call when operation completes
     */
    public void getUserItems(User user, FindCallback callback)
    {
        ParseQuery query = new ParseQuery("Item");
        query.whereEqualTo("owner", user.getParseUser());
        query.findInBackground(callback);
    }
	
		
	/**
	 * Resets the password using ParseUser methods
	 * @param email The email account of the associated account
	 * @param callback The method to call when the operation completes
	 */
	public void resetPassword(String email, RequestPasswordResetCallback callback)
	{
		
		ParseUser.requestPasswordResetInBackground(email, callback);
	}

    /**
     * Causes an item to save itself using the ParseObject it wraps
* @param i  The item to save
     */
    public void saveItem(Item i)
    {
        i.saveInBackground();
    }

    public void deleteItem(Item item, DeleteCallback deleteCallback)
    {
        item.deleteInBackground(deleteCallback);
    }

    public void getAllUsers(FindCallback findCallback)
    {
        ParseQuery query = ParseUser.getQuery();
        query.findInBackground(findCallback);
    }

    public void getUser(String email, FindCallback findCallback)
    {
        ParseQuery query = ParseUser.getQuery();
        query.whereEqualTo("username", email);
        query.findInBackground(findCallback);

    }

    public ParseObject lockUser(User currentUser)
    {
        ParseObject object = new ParseObject("LockedUsers");
        object.put("users",((ParseObject)currentUser.getParseUser()));
        object.put("username", currentUser.getEmail());
        object.saveInBackground();
        return object;
    }
    public void unlockUser(ParseObject currentUser)
    {
        if(currentUser.getClassName().equals("LockedUsers"))
        currentUser.deleteInBackground();
    }
    public ParseObject queueForDelete(User currentUser)
    {
        ParseObject object = new ParseObject("QueuedDelete");
        object.put("users",((ParseObject)currentUser.getParseUser()));
        object.put("username", currentUser.getEmail());
        object.saveInBackground();
        return object;

    }
  public void deleteCurrentUser()
  {
      ParseUser.getCurrentUser().deleteInBackground();
  }
	
	/*private class Pass extends SignUpCallback
    {
		
		boolean success;
		boolean finished;
		
		@Override
		public void done(ParseException e) {
		// TODO Auto-generated method stub
			success = e==null;
			finished = true;
		}
		
		public boolean isFinished(){
			return finished;
		}
	}
	
	private class Enter extends LogInCallback
    {

		boolean success;
		boolean finished;
		@Override
		public void done(ParseUser user, ParseException e) {
			finished = true; 
			success = e!=null;
		}
		
		public boolean isFinished(){
			return finished;
		}
	}    */
	

}
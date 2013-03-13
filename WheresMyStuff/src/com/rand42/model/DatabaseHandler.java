package com.rand42.model;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.parse.*;

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
	public void createUser(String email, String name, String password, boolean status, SignUpCallback callback)
	{
		ParseUser user = new ParseUser();
		user.setUsername(email);
		user.setPassword(password);
		user.put("name", name);
		user.signUpInBackground(callback);
		user.put("ADMIN", status);
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
	
    public List<User> getUserList(FindCallback callbacks){
    	//TODO: Finish getting user lists for admin stuff
    	ParseQuery query = ParseUser.getQuery();	
    	
    	List<User> output = null;
    	try {
			List<ParseObject> foo = query.find();
			output = new ArrayList<User>();
			for(ParseObject o : foo)
				output.add(new User((ParseUser) o));
		} catch (ParseException e) {
			//TODO: What else?
			e.printStackTrace();
		}
    	
    	return output;
    }
		
    //TODO: Stop repeating the same shit. This is silly.
    private List<ParseObject> search(String field, String target) throws ParseException{
    	
    	ParseQuery query = ParseUser.getQuery();
    	query.whereEqualTo(field, target);
    	
    	return query.find();
    }
    
    private List<ParseObject> put(String field, String target, String key, Object value) throws ParseException{
    	
    	List<ParseObject> foo = search(field,target);
    	if(foo!= null && !foo.isEmpty())
    		for(ParseObject o : foo)
    			o.put(key, value);
    	return foo;
    	
    }
    
    public void promoteUser(String email, FindCallback callback){
    	
    	try{
    		callback.done(put("email", email, "Admin", true), null);
    	
    	}catch(ParseException e){
    		callback.done(null, e);
    	}
    	
    }
    
    public void lockUser(String name, FindCallback callback){
    	
    	try{
    		callback.done(put("name", name, "Lock", true), null);
    	}catch(ParseException e){
    		callback.done(null, e);
    	}
    	
    }
    
    public void deleteUser(String name, FindCallback callback){
    	
    	try{
    		List<ParseObject> foo = search("name", name);
    		if(foo!= null && !foo.isEmpty())
    			for(ParseObject o : foo)
    				o.deleteInBackground();
    		
    		callback.done(foo, null);
    		
    	}catch(ParseException e){
    		callback.done(null, e);
    	}
    	
    }
	/**
	 * Resets the password using ParseUser methods
	 * @param email The email account of the associated account
	 * @param callback The method to call when the operation completes
	 */
	public void resetPassword(String email, RequestPasswordResetCallback callback){
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
	

}

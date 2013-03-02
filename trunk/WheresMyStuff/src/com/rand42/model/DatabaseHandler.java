package com.rand42.model;

import android.app.Activity;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;
/**
 * The DatabaseHandler is a singleton with Parse.com database read and write methods
 * @author Rand-42
 *
 */
public class DatabaseHandler 
{
	private static DatabaseHandler handler;
	private Callback onCall;
	
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
	 * Creates a new user using the ParseUser methods
	 * @param name The full name of the user
	 * @param email Email address
	 * 

	 * @param password Password
	 * @return A boolean indicating success
	 */
	public User login(String email, String password)
	{
		ParseUser.logInInBackground(email,password, new LogInCallback(){ public void done(ParseUser u, ParseException e){}  });
		
		return new User(ParseUser.getCurrentUser());
	}
	
	public boolean callFinished(){
		return onCall != null && onCall.isFinished();
	}
	
	public boolean createUser(String name, String email, String password)
	{
		ParseUser user = new ParseUser();
		user.setUsername(email);
		user.setPassword(password);
		user.put("name", name);
		Pass def = new Pass();
		user.signUpInBackground(def); 
		return def.success;
	}
	
		
	/**
	 * Resets the password using ParseUser methods
	 * @param email The email account of the associated account
	 * @return A boolean indicating success
	 */
	public void resetPassword(String email, RequestPasswordResetCallback callback)
	{
		
		ParseUser.requestPasswordResetInBackground(email, callback);
	}
	
	private class Pass extends SignUpCallback implements Callback{
		
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
	
	private class Enter extends LogInCallback implements Callback{

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
	}
	
	private interface Callback{
		boolean isFinished();
	}
}

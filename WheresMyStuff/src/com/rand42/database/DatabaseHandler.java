package com.rand42.database;

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
	 * @param password Password
	 * @return A boolean indicating success
	 */
	public boolean createUser(String name, String email, String password)
	{
		ParseUser user = new ParseUser();
		user.setUsername(email);
		user.setPassword(password);
		user.put("name", name);
		user.signUpInBackground(new SignUpCallback() 
		{
			public void done(ParseException e)
			{
				if(e==null)
				{
					//success
				}
				else
				{
					//fail. details in e
				}
			}
		});
		return true;
	}
	/**
	 * Resets the password using ParseUser methods
	 * @param email The email account of the associated account
	 * @return A boolean indicating success
	 */
	public boolean resetPassword(String email)
	{
		ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback()
		{
			public void done(ParseException e)
			{
				if(e==null)
				{
					//success
				}
				else
				{
					//fail
				}
			}
		});
		return true;
	}
}

package com.rand42.database;

import android.app.Activity;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

public class DatabaseHandler 
{
	private static DatabaseHandler handler;
	
	private DatabaseHandler()
	{
		
	}
	public static DatabaseHandler getHandler()
	{
		if(handler==null)
			handler = new DatabaseHandler();
		return handler;
	}
	
	public static boolean createUser(String name, String email, String password)
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
	public static boolean resetPassword(String email)
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

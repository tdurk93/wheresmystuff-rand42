package com.rand42.views;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rand42.factories.DialogFactory;
import com.rand42.model.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
/**
 * The MainActivity asks the user to login
 * @author Rand-42
 *
 */
public class MainActivity extends Activity {

	EditText emailField;
	EditText passwordField;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		emailField = (EditText)findViewById(R.id.emailField);
		passwordField = (EditText)findViewById(R.id.passwordField);
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	/**
	 * Called when the new user button is clicked. Starts a new Activity to create a new user
	 * @param view The clicked view
	 */
	public void newUserSignup(View view)
	{
		Intent newIntent = new Intent(this, NewUserActivity.class);
		startActivity(newIntent);
	}
	/**
	 * Called when the login button is clicked. Uses the ParseUser methods to log in the user. Calls either loginSuccess or loginFail
	 * @param view The clicked view
	 */
	public void submitLogin(View view)
	{
		DatabaseHandler db = DatabaseHandler.getHandler();
		db.login(emailField.getText().toString(),passwordField.getText().toString(), new LogInCallback()
		{
			public void done(ParseUser user, ParseException e)
			{
				if(user!=null)
				{
					loginSuccess();
				}
					
				else
				{
					loginFail(e);
				}
			}
		});
	}
	/**
	 * Called when the user successfully logs in. Starts a new Activity for the logged in user
	 */
	public void loginSuccess()
	{
			Log.i("MainActivity", ParseUser.getCurrentUser().getUsername());
			Intent i = new Intent(this, HomeActivity.class);
			emailField.setText("");
			passwordField.setText("");
			startActivity(i);
	}
	/**
	 * Called when the user fails to log in. Displays Dialog box informing user of error
	 * @param e The exception 
	 */
	public void loginFail(ParseException e)
	{
			AlertDialog dialog = DialogFactory.createStandardDialog("Login Failed", e.getMessage(),this);
			dialog.show();
	}
	/**
	 * Called when the reset button is clicked. Spawns a dialog that asks for email address. 
	 * @param view The clicked view
	 */
	public void passwordReset(View view)
	{
		AlertDialog dialog = DialogFactory.createResetDialog(this);
		dialog.show();
	}
	

	

	
}

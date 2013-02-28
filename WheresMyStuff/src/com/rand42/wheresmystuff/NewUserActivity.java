package com.rand42.wheresmystuff;

import java.util.regex.Pattern;

import com.parse.ParseException;
import com.parse.SignUpCallback;
import com.rand42.factories.DialogFactory;
import com.rand42.model.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Activity to create a new user in the system
 * @author Rand-42
 *
 */
public class NewUserActivity extends Activity {

	EditText nameField;
	EditText emailField;
	EditText passwordField;
	EditText confirmField;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	Pattern pattern;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		nameField = (EditText)findViewById(R.id.nameField);
		emailField = (EditText)findViewById(R.id.emailField);
		passwordField = (EditText)findViewById(R.id.passwordField);
		confirmField = (EditText)findViewById(R.id.confirmField);
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_user, menu);
		return true;
	}
	/**
	 * Called when the create button is clicked. Validates information and creates a new user 
	 * @param view The clicked view
	 */
	public void createUser(View view)
	{
		String name = nameField.getText().toString();
		String email = emailField.getText().toString();
		String password = passwordField.getText().toString();
		String confirm = confirmField.getText().toString();
		if(pattern.matcher(email).matches()&&password.equals(confirm)) //valid email and matching passwords
		{
			DatabaseHandler db = DatabaseHandler.getHandler();
			db.createUser(name,email,password, new SignUpCallback()
			{
				public void done(ParseException e)
				{
					if(e==null)
					{
						createUserSuccess();
					}
					else
					{
						createUserFail(e);
					}
				}
			});
			
		}
		else
		{
			AlertDialog dialog = DialogFactory.createStandardDialog("Error","Something went wrong. Is your email valid? Do your passwords match?",this);
			dialog.show();
		}
			
	}
	/**
	 * Called when creating a new user completed successfully
	 */
	public void createUserSuccess()
	{
		AlertDialog dialog = DialogFactory.createFinishDialog("Success","You will recieve a confirmation email",this);
		dialog.show();
	}
	/**
	 * Called when created a new user failed
	 * @param e Exception, details included
	 */
	public void createUserFail(ParseException e)
	{
		AlertDialog dialog = DialogFactory.createStandardDialog("Error",e.getMessage(),this);
		dialog.show();
	}

	

}

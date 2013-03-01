package com.rand42.wheresmystuff;

import java.util.regex.Pattern;

import com.rand42.factories.DialogFactory;
import com.rand42.model.DatabaseHandler;
import com.rand42.model.LocalModel;
import com.rand42.model.Model;

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
 *TODO: Consolidate callbacks
 */
public class NewUserActivity extends Activity {

	private EditText nameField;
	private EditText emailField;
	private EditText passwordField;
	private EditText confirmField;
	private Model model;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		nameField = (EditText)findViewById(R.id.nameField);
		emailField = (EditText)findViewById(R.id.emailField);
		passwordField = (EditText)findViewById(R.id.passwordField);
		confirmField = (EditText)findViewById(R.id.confirmField);
		pattern = Pattern.compile(EMAIL_PATTERN);
		model = LocalModel.getModel();
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
		if(verify(email,password,confirm)) //valid email and matching passwords
			if(model.addUser(name,email,password))
				createUserSuccess();
			else
				createUserFail();
	
	}
	
	/**
	 * Herein we verify the email, password and confirmation.
	 * We probably want to put in some error propagation
	 * in the future. -S
	 * 
	 * @param email
	 * @param password
	 * @param confirm
	 * @return
	 */
	private boolean verify(String email, String password, String confirm){
		
		boolean valid = true;
		
		String errorMsg = "";
		
		if(! (valid = pattern.matcher(email).matches())) //trust me I got this. -S
			errorMsg = "Email misformatted";
		
		if(!password.equals(confirm) && !(valid = false)) //short circuits wat -S
			errorMsg = "Passwords don't match";
		
		
		DialogFactory.createStandardDialog("Error", errorMsg, this).show();	
		
		return valid;
		
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
	public void createUserFail()
	{
		AlertDialog dialog = DialogFactory.createStandardDialog("Error","User could not be created",this);
		dialog.show();
	}

}

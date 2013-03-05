package com.rand42.views;

import com.rand42.factories.DialogFactory;
import com.rand42.model.LocalModel;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.rand42.presenters.LoginPresenter;
import com.rand42.views.interfaces.ILoginView;

/**
 * The LoginActivity asks the user to login
 * @author Rand-42
 *
 */
public class LoginActivity extends Activity implements ILoginView
{

	private EditText emailField;
	private EditText passwordField;

	private LoginPresenter presenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

        emailField = (EditText)findViewById(R.id.emailField);
		passwordField = (EditText)findViewById(R.id.passwordField);
		presenter = new LoginPresenter(this, LocalModel.getModel());
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
		presenter.logIn(emailField.getText().toString(), passwordField.getText().toString());
	}
	/**
	 * Called when the user successfully logs in. Starts a new Activity for the logged in user
	 */
	public void loginSuccess()
	{
			Intent i = new Intent(this, HomeActivity.class);
			emailField.setText("");
			passwordField.setText("");
			startActivity(i);
	}
	/**
	 * Called when the user fails to log in. Displays Dialog box informing user of error
     * @param message
     */
	public void loginFail(String message)
	{
			AlertDialog dialog = DialogFactory.createStandardDialog("Login Failed", message,this);
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

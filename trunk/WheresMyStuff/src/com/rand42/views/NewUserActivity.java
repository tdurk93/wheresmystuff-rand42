package com.rand42.views;

import java.util.regex.Pattern;

import com.rand42.factories.DialogFactory;
import com.rand42.model.IModel;
import com.rand42.model.LocalModel;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.rand42.presenters.LoginPresenter;
import com.rand42.presenters.NewUserPresenter;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Activity to create a new user in the system
 * @author Rand-42
 *TODO: Consolidate callbacks
 */
public class NewUserActivity extends RoboActivity implements INewUserView {

	@InjectView(R.id.nameField) private EditText nameField;
	@InjectView(R.id.emailField) private EditText emailField;
	@InjectView(R.id.passwordField) private EditText passwordField;
	@InjectView(R.id.confirmField) private EditText confirmField;
	private NewUserPresenter presenter;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
	    presenter = new NewUserPresenter(this, LocalModel.getModel());
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
		presenter.createUser(email,name, password, confirm);
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

	/**
	 * Called when creating a new user completed successfully
	 */
	public void createSuccess()
	{
		AlertDialog dialog = DialogFactory.createFinishDialog("Success","You will recieve a confirmation email",this);
		dialog.show();
	}
	/**
	 * Called when created a new user failed
	 * @param e Exception, details included
	 */
	public void createFail(String message)
	{
		AlertDialog dialog = DialogFactory.createStandardDialog("Error",message,this);
		dialog.show();
	}

}

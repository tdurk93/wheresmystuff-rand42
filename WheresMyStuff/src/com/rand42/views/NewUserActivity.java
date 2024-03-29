package com.rand42.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.rand42.factories.DialogFactory;
import com.rand42.model.LocalModel;
import com.rand42.presenters.NewUserPresenter;
import com.rand42.views.interfaces.INewUserView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Activity to create a new user in the system
 * @author Rand-42
 *TODO: Consolidate callbacks
 */
public class NewUserActivity extends RoboActivity implements INewUserView
{

	@InjectView(R.id.nameField) private EditText nameField;
	@InjectView(R.id.emailField)private EditText emailField;
	@InjectView(R.id.passwordField) private EditText passwordField;
	@InjectView(R.id.confirmField) private EditText confirmField;

    private NewUserPresenter presenter;
    private boolean adminCreation;
	

        /**
         * Called when a NewUserActivity is created
         * @param savedInstanceState a previously saved state, if applicable
         */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		

	    presenter = new NewUserPresenter(LocalModel.getModel(),this);
        adminCreation = getIntent().getBooleanExtra("isAdmin",false);

        nameField.addTextChangedListener(new EmptyTextWatcher(nameField));
        passwordField.addTextChangedListener(new EmptyTextWatcher(passwordField));
        confirmField.addTextChangedListener(new EmptyTextWatcher(confirmField));
        emailField.addTextChangedListener(new EmailTextWatcher(emailField));
        getActionBar().setTitle("New User");
	}
        
        /**
         * Called to create the options menu when creating a user
         * @param menu the menu to build on
         * @return 
         */
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
		presenter.createUser(email,name, password,  confirm, adminCreation);
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
		AlertDialog dialog = DialogFactory.createFinishDialog("Success","Account Created Successfully",this);
		dialog.show();
	}
	/**
	 * Called when created a new user failed
	 */
	public void createFail(String message)
	{
		AlertDialog dialog = DialogFactory.createStandardDialog("Error",message,this);
		dialog.show();
	}

}

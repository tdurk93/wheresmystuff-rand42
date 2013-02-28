package com.rand42.views;

import java.util.regex.Pattern;

import com.rand42.factories.DialogFactory;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.rand42.presenters.CreateUserPresenter;

/**
 * Activity to create a new user in the system
 * @author Rand-42
 *
 */
public class NewUserActivity extends Activity implements ICreateUserView {

	EditText nameField;
	EditText emailField;
	EditText passwordField;
	EditText confirmField;
    private CreateUserPresenter presenter;

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
        presenter = new CreateUserPresenter(this);
		
		nameField = (EditText)findViewById(R.id.nameField);
		emailField = (EditText)findViewById(R.id.emailField);
		passwordField = (EditText)findViewById(R.id.passwordField);
		confirmField = (EditText)findViewById(R.id.confirmField);

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

		    presenter.createUserClick(name, email, password, confirm);


	}
	/**
	 * Called when creating a new user completed successfully
	 */
	public void success()
	{
		AlertDialog dialog = DialogFactory.createFinishDialog("Success","You will recieve a confirmation email",this);
		dialog.show();
	}
	/**
	 * Called when created a new user failed. Prompts appropriate dialogBox
     * @param fail The reason fail was called
	 */
	public void fail(CreateUserPresenter.CreateUserFail fail)
	{
        AlertDialog dialog;
        switch(fail)
        {
            case invalidData:
               dialog = DialogFactory.createStandardDialog("Error","Your passwords dont match, or your email is invalid",this);
                dialog.show();
                break;
            case exists:
                 dialog = DialogFactory.createStandardDialog("Error", "This email address already exists in our system",this);
                dialog.show();
                break;
            case timeout:
                dialog = DialogFactory.createStandardDialog("Error", "We could not connect to the database. Check your connection", this);
                dialog.show();
                break;
            case unknown:
                dialog = DialogFactory.createStandardDialog("Error", "Unknown Error. Try again later", this);
                dialog.show();
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value");
        }

	}

	

}

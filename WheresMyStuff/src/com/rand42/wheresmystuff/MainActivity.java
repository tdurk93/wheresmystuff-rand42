package com.rand42.wheresmystuff;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rand42.database.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
	public void newUserSignup(View view)
	{
		Intent newIntent = new Intent(this, NewUserActivity.class);
		startActivity(newIntent);
	}
	public void submitLogin(View view)
	{
		//Working properly. According to design standards? Needs data validation and better notification
		ParseUser.logInInBackground(emailField.getText().toString(),passwordField.getText().toString(), new LogInCallback()
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
	public void loginSuccess()
	{
			Log.i("MainActivity", ParseUser.getCurrentUser().getUsername());
			Intent i = new Intent(this, HomeActivity.class);
			emailField.setText("");
			passwordField.setText("");
			startActivity(i);
	}
	public void loginFail(ParseException e)
	{
			AlertDialog dialog = createStandardDialog("Login Failed", "Invalid Username or Password");
			dialog.show();
	}
	public void passwordReset(View view)
	{
		AlertDialog dialog = createResetDialog();
		dialog.show();
	}
	
	public AlertDialog createStandardDialog(String title, String message)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setPositiveButton("Ok",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog,
                             int which) {
                         dialog.dismiss();
                     }
                 });
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setCancelable(false);
		return alertDialogBuilder.create();
	}

	public AlertDialog createResetDialog()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);
		alertDialogBuilder.setView(input);
		alertDialogBuilder.setTitle("Enter Email");
		alertDialogBuilder.setPositiveButton("Ok",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog,
                             int which) {
                    	 DatabaseHandler db = DatabaseHandler.getHandler();
                    	 db.resetPassword(input.getText().toString());
                    	 AlertDialog infoDialog = createStandardDialog("","You will recieve an email shortly");
                    	 infoDialog.show();
                         dialog.dismiss();
                     }
                 });
		alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                            int which) {
                  
                        dialog.dismiss();
                    }
                });
		alertDialogBuilder.setMessage("Enter your email");
		alertDialogBuilder.setCancelable(false);
		return alertDialogBuilder.create();
	}
}

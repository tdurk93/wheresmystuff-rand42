package com.rand42.wheresmystuff;

import java.util.regex.Pattern;

import com.rand42.database.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
	
	public void createUser(View view)
	{
		String name = nameField.getText().toString();
		String email = emailField.getText().toString();
		String password = passwordField.getText().toString();
		String confirm = confirmField.getText().toString();
		if(pattern.matcher(email).matches()&&password.equals(confirm)) //valid email and matching passwords
		{
			DatabaseHandler db = DatabaseHandler.getHandler();
			db.createUser(name,email,password);
			AlertDialog dialog = createStandardDialog("Success","You will recieve a confirmation email");
			dialog.show();
			
		}
		else
		{
			AlertDialog dialog = createStandardDialog("Error","Something went wrong. Is your email valid? Do your passwords match?");
			dialog.show();
		}
			
		
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

}

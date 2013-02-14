package com.rand42.wheresmystuff;

import com.parse.ParseUser;
import com.rand42.database.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void submitLogin(View view)
	{
		//Working properly. According to design standards? Needs data validation and better notification
		DatabaseHandler db = DatabaseHandler.getHandler();
		EditText emailField = (EditText)findViewById(R.id.emailField);
		EditText passwordField = (EditText)findViewById(R.id.passwordField);
		db.login(emailField.getText().toString(), passwordField.getText().toString());
		if(ParseUser.getCurrentUser()!=null)
		{
			Log.i("MainActivity", ParseUser.getCurrentUser().getUsername());
		}
		Log.i("MainActivity","Clicked");
	}

}

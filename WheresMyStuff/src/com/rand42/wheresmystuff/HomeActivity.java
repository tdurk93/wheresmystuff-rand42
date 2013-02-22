package com.rand42.wheresmystuff;

import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
/**
 * Activity representing the home page for the logged in user
 * @author Rand-42
 *
 */
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		TextView textView = (TextView)findViewById(R.id.confirmText);
		textView.setText("Logged in as "+ParseUser.getCurrentUser().getUsername());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.menu_logout:
			ParseUser.logOut();
			this.finish();
			return true;
		default:
			return true;
		}
	}

}

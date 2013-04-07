package com.rand42.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.rand42.model.LocalModel;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminActivity extends Activity
{
    /**
     * Called when the AdminActivity is opened on the device.
     * @param savedInstanceState A "bundle" of all of the variables/values 
     * associated with the state of the saved instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getActionBar().setTitle("Admin Home");
    }
    
    /**
     * Called when the "Create User" button is pressed
     * @param v The button pressed, represented as a View object
     */
    public void createClick(View v)
    {
        Intent i = new Intent(this, NewUserActivity.class);
        i.putExtra("isAdmin", true);
        startActivity(i);
    }
    
    /**
     * Called when the "View User" button is pressed
     * @param v The button pressed, represented as a View object
     */
    public void viewClick(View v)
    {
        Intent i = new Intent(this, UserListActivity.class);
        startActivity(i);
    }
    
    /**
     * Called when the button to return to the main screen of the app is pressed
     * @param v The button pressed, represented as a View object
     */
    public void regularClick(View v)
    {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
    
    /**
     * Called when the device's back button is pressed
     */
    @Override
    public void onBackPressed()
    {
        LocalModel.getModel().logOut();
        this.finish();
    }
}
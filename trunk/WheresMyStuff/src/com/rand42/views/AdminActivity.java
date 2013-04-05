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
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getActionBar().setTitle("Admin Home");
    }
    public void createClick(View v)
    {
        Intent i = new Intent(this, NewUserActivity.class);
        i.putExtra("isAdmin", true);
        startActivity(i);
    }
    public void viewClick(View v)
    {
        Intent i = new Intent(this, UserListActivity.class);
        startActivity(i);
    }
    public void regularClick(View v)
    {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed()
    {
        LocalModel.getModel().logOut();
        this.finish();
    }
}
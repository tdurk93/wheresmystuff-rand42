package com.rand42.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewItemActivity extends RoboActivity
{
    @InjectView(R.id.itemNameField) private EditText nameField;
    @InjectView(R.id.itemDescField) private EditText descField;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
    }
}
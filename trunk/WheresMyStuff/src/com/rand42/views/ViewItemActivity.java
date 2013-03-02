package com.rand42.views;

import android.app.Activity;
import android.os.Bundle;
import roboguice.activity.RoboActivity;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewItemActivity extends RoboActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
    }
}
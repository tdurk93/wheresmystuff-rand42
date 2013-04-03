package com.rand42;

import android.app.Application;
import android.content.Context;
import com.rand42.model.LocalModel;


public class WheresMyStuffApplication extends Application
{
    private static Context context;
	@Override
	public void onCreate()
	{
        super.onCreate();
        WheresMyStuffApplication.context = getApplicationContext();
        LocalModel.getModel().addUser("admin","admin","admin",true);

    }

    public static Context getAppContext()
    {
        return WheresMyStuffApplication.context;
    }


}

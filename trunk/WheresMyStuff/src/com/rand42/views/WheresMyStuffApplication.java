package com.rand42.views;

import com.parse.Parse;
import com.parse.ParseACL;

import android.app.Application;


public class WheresMyStuffApplication extends Application
{
	@Override
	public void onCreate()
	{
		//Connect to parse.com
		Parse.initialize(this, "KoYndP2ZTbsZI02r1s7vOFVBZGMATSQdIOxnG3BU", "Cgz0sIyzHfdCZe4Sd0d5NsFqKQcnLZQPk7FMkdAM");
		ParseACL thisAcl = new ParseACL();
		thisAcl.setPublicReadAccess(true);
		ParseACL.setDefaultACL(thisAcl, true);

    }

}

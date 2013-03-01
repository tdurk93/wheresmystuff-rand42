package com.rand42.model;

import java.util.*;
import android.os.*;
import android.util.Log;
/**
 * 
 * Here lies the in-memory model.
 * It maintains the state of the app.
 * 
 * @author Stefano
 *
 */
public class LocalModel implements Model{
	
	private DatabaseHandler dbh;
	private User currentUser;
	private static Model model;
	private SecurityManager sm;
	
	/**
	 * The local model is a singleton.
	 * @return
	 */
	public static Model getModel(){
		if(model == null)
			model = new LocalModel();
		return model;
	}
	
	/**
	 * We really need to have less of these
	 */
	private LocalModel(){
		dbh = DatabaseHandler.getHandler();
		sm = SecurityManager.getSecurityManager();
	}
	
	
	@Override
	public boolean logIn(String name, String password) {
		currentUser = dbh.login(name, password);

		if(!sm.check(name))
			currentUser = null;
		
		return null != currentUser;
	}

	@Override
	public boolean addUser(String name, String email, String password){
		
		return dbh.createUser(name, email, password);
		
	}

	@Override
	public User getUser() {
		return currentUser;
	}

	@Override
	public void logOut() {
		if(currentUser!= null) currentUser.logOut();
		currentUser= null;
	}
	

}

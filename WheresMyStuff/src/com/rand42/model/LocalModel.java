package com.rand42.model;

import com.parse.LogInCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;

/**
 * 
 * Here lies the in-memory model.
 * It maintains the state of the app.
 * 
 * @author Stefano
 *
 */
public class LocalModel implements IModel
{
	
	private DatabaseHandler dbh;
	private User currentUser;
    private ArrayList<Item> userItems;
	private static IModel model;
	private SecurityManager sm;
	
	/**
	 * The local model is a singleton.
	 * @return
	 */
	public static IModel getModel(){
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

	public void logIn(String name, String password, LogInCallback callback)
    {
		dbh.login(name, password, callback);
	}


    public boolean checkUserAttempts(String email)
    {
          return(sm.check(email));
    }

    @Override
    public void createItem(String name, String description, User owner)
    {
        Item item = new Item(name, owner, description, new Location(0,0));
        if(userItems==null)
            userItems = new ArrayList<Item>();
        userItems.add(item);
        item.saveInBackground();

    }

    @Override
	public void addUser(String email, String name, String password, SignUpCallback callback)
    {
		dbh.createUser(email, name, password, callback);
	}

	@Override
	public User getUser() {
		return currentUser;
	}
    public void setUser(User user)
    {
        currentUser = user;
    }

	@Override
	public void logOut() {
		if(currentUser!= null) currentUser.logOut();
		currentUser= null;
	}


	

}

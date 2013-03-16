package com.rand42.model;

import com.rand42.database.ItemsDataSource;
import com.rand42.database.UsersDataSource;
import com.rand42.views.WheresMyStuffApplication;

import java.util.*;

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
	
	private ItemsDataSource ids;
    private UsersDataSource uds;
	private User currentUser;
    //private Map<String, Item> userItems;
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
		ids = new ItemsDataSource(WheresMyStuffApplication.getAppContext());
        uds = new UsersDataSource(WheresMyStuffApplication.getAppContext());
		sm = SecurityManager.getSecurityManager();
	}
	
	
	@Override
	public boolean logIn(String email, String password)
    {
		currentUser = uds.loginUser(email,password);
        return currentUser ==null;
	}

    @Override
    public boolean checkUserAttempts(String email)
    {
          return(sm.check(email));
    }

    @Override
    public void createItem(String name, String description, User owner, boolean lost)
    {
        ids.createItem(name,description,owner,lost);
    }
    @Override
    public Item getItemById(long id)
    {
        //need ids method
       return null;
    }

    @Override
    public void deleteItem(Item item)
    {
        ids.deleteItem(item.getID());
    }

    @Override
    public void resetAttempts(String email)
    {
        sm.reset(email);
    }

    @Override
    public User getUser(String email)
    {
        return uds.getUserByEmail(email);
    }

    @Override
    public void lockUser(User currentUser)
    {
        //TODO implement
    }

    @Override
    public void unlockUser(User currentUser)
    {
        //TODO implement

    }

    @Override
    public boolean isUserLocked(User user)
    {
        return false;
        //TODO implement or delete
    }

    @Override
    public void queueUserDelete(User user)
    {

    }

    @Override
    public void performUserDelete(User user)
    {

    }

    @Override
    public boolean isUserQueued(User user)
    {
          return false;
    }

    public List<User> getAllUsers()
    {
        return uds.getAllUsers();
    }
    @Override
    public List<Item> getUserItems(User user)
    {
        return ids.getAllUserItems(user);
    }


    @Override
	public void addUser(String email, String name, String password, boolean isAdmin)
    {
		uds.createUser(name,email,password, isAdmin);
	}


	@Override
	public User getCurrentUser() {
		return currentUser;
	}
    @Override
    public void setCurrentUser(User user)
    {
        currentUser = user;
    }

	@Override
	public void logOut() {
      currentUser = null;
	}


	

}

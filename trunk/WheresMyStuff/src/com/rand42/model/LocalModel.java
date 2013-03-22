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
    private UsersDataSource
            uds;
	private User currentUser;
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
        uds.open();
        ids.open();
	}
	
	
	@Override
	public boolean logIn(String email, String password)
    {

		currentUser = uds.loginUser(email,password);
        return currentUser != null;
	}

    @Override
    public boolean checkUserAttempts(String email)
    {
          return(sm.check(email));
    }

    @Override
    public void createItem(String name, String description, User owner, Date date, boolean lost)
    {
        ids.createItem(name,description,owner, date, lost);
    }
    @Override
    public Item getItemById(long id)
    {
        return ids.getItemById(id);
    }


    @Override
    public boolean deleteItem(Item item)
    {
        return ids.deleteItem(item.getID());
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
    public User getUser(long id)
    {
        return uds.getUserById(id);
    }

    @Override
    public void lockUser(User currentUser)
    {
        uds.lockUser(currentUser.getID());
    }

    @Override
    public void unlockUser(User currentUser)
    {
        uds.unlockUser(currentUser.getID());

    }



    @Override
    public boolean deleteUser(User user)
    {
         return uds.deleteUser(user.getID());
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
	public boolean addUser(String email, String name, String password, boolean isAdmin)
    {
		return uds.createUser(name,email,password, isAdmin);
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

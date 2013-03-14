package com.rand42.model;

import com.parse.*;

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
	
	private DatabaseHandler dbh;
	private User currentUser;
    private Map<String, Item> userItems;
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

    @Override
    public boolean checkUserAttempts(String email)
    {
          return(sm.check(email));
    }

    @Override
    public void createItem(String name, String description, User owner, boolean lost)
    {
        Item item = new Item(name, owner, description, new Location(0,0), lost);
        if(userItems==null)
            userItems = new HashMap<String, Item>();
        DatabaseHandler dbh = DatabaseHandler.getHandler();
        dbh.saveItem(item);
        userItems.put(item.getUID(), item);
    }
    @Override
    public Item getItem(String uid)
    {
        if(userItems.containsKey(uid))
            return userItems.get(uid);
       return null;
    }

    @Override
    public void deleteItem(final Item item, final Requestor<Item> requestor)
    {

        DatabaseHandler dbh = DatabaseHandler.getHandler();
        dbh.deleteItem(item, new DeleteCallback()
        {
            public void done(ParseException e)
            {
                userItems.remove(item.getUID());
                requestor.querySuccess(userItems.values());
            }
        });

    }

    @Override
    public void resetAttempts(String email)
    {
        sm.reset(email);
    }

    @Override
    public void getUser(String email, final Requestor<User> requestor)
    {
        DatabaseHandler dbh = DatabaseHandler.getHandler();
        dbh.getUser(email, new FindCallback()
        {
           public void done(List<ParseObject> results, ParseException e)
           {
               if(e==null)
               {
                   List<User> processedResults = new ArrayList<User>();
                   processedResults.add(new User( ((ParseUser)results.get(0))) );
                   requestor.querySuccess(processedResults);
               }
           }
        });
    }

    @Override
    public void lockUser(User currentUser)
    {
        DatabaseHandler dbh = DatabaseHandler.getHandler();
        ParseObject lockedUser = dbh.lockUser(currentUser);
        sm.lockUser(lockedUser);
    }

    @Override
    public void unlockUser(User currentUser)
    {
        ParseObject unlockedUser = sm.unlockUser(currentUser.getEmail());
        DatabaseHandler dbh = DatabaseHandler.getHandler();
        dbh.unlockUser(unlockedUser);

    }

    @Override
    public boolean isUserLocked(User user)
    {
        return sm.isUserLocked(user);
    }

    @Override
    public void queueUserDelete(User user)
    {
        ParseObject queuedUser = dbh.queueForDelete(user);
        sm.queueDelete(queuedUser);
    }

    @Override
    public void performUserDelete(User user)
    {
        ParseObject queuedUser =sm.performDelete(user.getEmail());
        //if(queuedUser.get("users")==currentUser)
        dbh.deleteCurrentUser();
    }

    @Override
    public boolean isUserQueued(User user)
    {
        return sm.isUserQueued(user);
    }

    public void getAllUsers(final Requestor<User> requestor)
    {
        DatabaseHandler dbh = DatabaseHandler.getHandler();
        dbh.getAllUsers(new FindCallback()
        {
            public void done(List<ParseObject> results, ParseException e)
            {
                List<User> processedResults = new ArrayList<User>();
                if(e==null)
                {
                    for(ParseObject o:results)
                    {
                        ParseUser u = (ParseUser)o;
                        processedResults.add(new User(u));
                    }
                    requestor.querySuccess(processedResults);
                }
            }
        });

    }
    @Override
    public void getUserItems(User user, final Requestor<Item> requestor)
    {

        if(user==currentUser&&userItems!=null)
        {
            requestor.querySuccess(userItems.values());
        }
        else
        {

            final boolean isCurrent=user==currentUser;
            if(isCurrent)
            {
                userItems=new HashMap<String, Item>();
            }
            DatabaseHandler dbh = DatabaseHandler.getHandler();
            dbh.getUserItems(user, new FindCallback()
            {
                @Override
                public void done(List<ParseObject> parseObjects, ParseException e)
                {
                    if(e==null)
                    {
                        List<Item> items = new ArrayList<Item>();
                        for(ParseObject po:parseObjects)
                        {
                            Item i = new Item(po);
                            items.add(i);
                            if(isCurrent)
                                userItems.put(po.getString("uid"),i);
                        }
                        requestor.querySuccess(items);

                    }
                }

            });
        }

    }


    @Override
	public void addUser(String email, String name, String password, boolean isAdmin, SignUpCallback callback)
    {
		dbh.createUser(email, name, password, isAdmin, callback);
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
        userItems = null;
		if(currentUser!= null) currentUser.logOut();
		currentUser= null;
	}


	

}

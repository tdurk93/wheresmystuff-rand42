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
	public void addUser(String email, String name, String password, SignUpCallback callback)
    {
		dbh.createUser(email, name, password, callback);
	}

	@Override
	public User getUser() {
		return currentUser;
	}
    @Override
    public void setUser(User user)
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

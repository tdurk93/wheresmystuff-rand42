package com.rand42.factories;

import java.util.ArrayList;
import java.util.List;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.rand42.model.Item;
import com.rand42.model.User;

public class CallbackFactory {

	
	//TODO: implement.
	
	//Writing anonymous callbacks that we then reuse is silly.
	
	
	//From LocalModel:
	/*
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
	
	    
	        
	        dbh.deleteItem(item, new DeleteCallback()
        {
            public void done(ParseException e)
            {
                userItems.remove(item.getID());
                requestor.querySuccess(userItems.values());
            }
        });
        
        
*/
	
	//From User:
	
	/*
	 * 
            target.fetchIfNeededInBackground(new GetCallback() //some targets may be skeletons without data. must load here
            {
                @Override
                public void done(ParseObject parseObject, ParseException e)
                {
                    ParseUser parseUser = (ParseUser)parseObject;
                    user =parseUser;
                    name = parseUser.getUsername();
                    id = parseUser.getObjectId();
                    email = parseUser.getEmail();
                    isAdmin = parseUser.getBoolean("ADMIN"); //I hope this works -S
                }
            });
            
	 */
	
	//From Item:
	
	/*
	 *         target.fetchIfNeededInBackground(new GetCallback()  //target is often just a skeleton with no data. must load here
        {
            @Override
            public void done(ParseObject parseObject, ParseException e)
            {
                name=parseObject.getString("name");
                description=parseObject.getString("desc");
                owner = new User((ParseUser)parseObject.getParseObject("owner"));
                uid = parseObject.getString("uid");
                lost = parseObject.getBoolean("lost");

            }
        });
	 */
	
	//From the presenters:
	
	/*
	 *             model.addUser(email, name, password, new SignUpCallback()
            {
               public void done(ParseException e)
               {
                    if(e==null)
                        view.createSuccess();
                   else
                        view.createFail(e.getMessage());

               }
            });
	 */
	
	/*
	 *             model.addUser(email, name, password, true, new SignUpCallback()
            {
               public void done(ParseException e)
               {
                    if(e==null)
                        view.createSuccess();
                   else
                        view.createFail(e.getMessage());

               }
            });
	 */
	
	/*
	 * 
	 * model.promoteUser(email, new FindCallback(){

			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				
			} 
			});
	 */
	
	/*
	 *             model.logIn(email, password, new LogInCallback(){
                public void done(ParseUser u, ParseException e)
                {
                    if(u!=null)
                    {
                        model.setUser(new User(u));
                        view.loginSuccess();

                    }
                    else
                    {
                        view.loginFail(e.getMessage());
                        model.setUser(null);
                    }

                }
            });
	 */
}

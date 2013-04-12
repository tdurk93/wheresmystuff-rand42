package com.rand42.tests;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.ItemFilter;
import com.rand42.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/7/13
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginTestModel implements IModel
{
    User user;
    @Override
    public boolean logIn(String email, String password)
    {
        if(email.equals("correct")&&password.equals("correct"))
        {
            user = new User("","","",false,1,true);
            return true;
        }
        if(email.equals("locked")&&password.equals("locked"))
        {
             user = new User("","","",false,1,false);
            return true;
        }

        return false;
    }

    @Override
    public boolean addUser(String email, String name, String password, boolean isAdmin)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void logOut()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User getCurrentUser()
    {
        return user;
    }

    @Override
    public List<User> getAllUsers()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCurrentUser(User user)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean checkUserAttempts(String string)
    {
        if(string.equals("attempts"))
            return false;
        return true;
    }

    @Override
    public void createItem(String name, String description, User owner, Date date, boolean lost, String category)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Item> getUserItems(User user)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Item getItemById(long id)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteItem(Item item)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resetAttempts(String email)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User getUser(String email)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User getUser(long id)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void lockUser(User currentUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void unlockUser(User currentUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setFilter(ItemFilter filter)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ItemFilter getFilter()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteUser(User user)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Item> searchItems(String query)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

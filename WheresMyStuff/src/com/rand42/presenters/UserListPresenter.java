package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Requestor;
import com.rand42.model.User;
import com.rand42.views.interfaces.IUserListView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserListPresenter implements Requestor<User>
{
    private final IModel model;
    private final IUserListView view;

    public UserListPresenter(IModel model, IUserListView view)
    {
        this.model = model;
        this.view = view;
    }


    @Override
    public void querySuccess(Collection<User> users)
    {
        ArrayList<User> toRemove = new ArrayList<User>();
        for(User u:users)
        {
            if(!u.isActive())
            {
                toRemove.add(u);
            }
            if(u.getEmail().equals(model.getCurrentUser().getEmail()))
                toRemove.add(u);
        }
        for(User u:toRemove)
            users.remove(u);

        view.userQuerySuccess(users);
    }
    public void getUsers()
    {
        model.getAllUsers(this);
    }

    @Override
    public void queryFail(String message)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

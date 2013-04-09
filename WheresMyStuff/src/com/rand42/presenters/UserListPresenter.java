package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.LocalModel;
import com.rand42.model.User;
import com.rand42.views.interfaces.IUserListView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserListPresenter
{
    private final IModel model;
    private final IUserListView view;

    public UserListPresenter(IModel model, IUserListView view)
    {
        this.model = model;
        this.view = view;
    }


    public List<User> getUsers()
    {
       return model.getAllUsers();
    }

}

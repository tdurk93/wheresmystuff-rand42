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

    /**
     * A presenter for a list of users
     * @param model
     * @param view
     */
    public UserListPresenter(IModel model, IUserListView view)
    {
        this.model = model;
        this.view = view;
    }


    /**
     * Gets the list of all users
     * @return a list of all users
     */
    public List<User> getUsers()
    {
       return model.getAllUsers();
    }

}

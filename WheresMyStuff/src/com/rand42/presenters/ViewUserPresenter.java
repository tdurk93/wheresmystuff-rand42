package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.LocalModel;
import com.rand42.model.User;
import com.rand42.views.interfaces.IViewUserView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 11:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewUserPresenter
{
    private final IModel model;
    private final IViewUserView view;

    public ViewUserPresenter(IViewUserView view)
    {
        this.model = LocalModel.getModel();
        this.view = view;
    }

    public User loadUser(long id)
    {
        return model.getUser(id);
    }


    public void lockUser(User currentUser)
    {
        model.lockUser(currentUser);

    }
    public void unlockUser(User user)
    {
        model.unlockUser(user);
    }

    public boolean deleteUser(User currentUser)
    {

        return model.deleteUser(currentUser);
    }
}

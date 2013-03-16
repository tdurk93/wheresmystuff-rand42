package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Requestor;
import com.rand42.model.User;
import com.rand42.views.interfaces.IViewUserView;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 11:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewUserPresenter implements Requestor<User>
{
    private final IModel model;
    private final IViewUserView view;

    public ViewUserPresenter(IModel model, IViewUserView view)
    {
        this.model = model;
        this.view = view;
    }

    public void loadUser(String email)
    {
        model.getUser(email);
    }

    @Override
    public void querySuccess(Collection<User> users)
    {
        User[] usersArr = Arrays.copyOf(users.toArray(), users.toArray().length, User[].class);

        view.presentData(usersArr[0], model.isUserLocked(usersArr[0]));
    }

    @Override
    public void queryFail(String message)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void lockUser(User currentUser)
    {
        model.lockUser(currentUser);

    }
    public void unlockUser(User user)
    {
        model.unlockUser(user);
    }

    public void deleteUser(User currentUser)
    {
        currentUser.setInactive();
        model.queueUserDelete(currentUser);
    }
}

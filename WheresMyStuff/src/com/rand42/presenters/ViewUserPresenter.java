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

    /**
     * Default constructor for view user presented
     * @param model
     * @param view
     */
    public ViewUserPresenter(IModel model, IViewUserView view)
    {
        this.model = model;
        this.view = view;
    }

    /**
     * Returns a user given the user's id
     * @param the user's id number
     * @return that user
     */
    public User loadUser(long id)
    {
        return model.getUser(id);
    }


    /**
     * Locks a user account
     * @param user account to lock
     */
    public void lockUser(User currentUser)
    {
        model.lockUser(currentUser);

    }
    
    /**
     * unlocks a user account
     * @param user account to unlock
     */
    public void unlockUser(User user)
    {
        model.unlockUser(user);
    }

    
    /**
     * Deletes a user
     * @param user to delete
     * @return whether or not user was deleted
     */
    public boolean deleteUser(User currentUser)
    {

        return model.deleteUser(currentUser);
    }
}

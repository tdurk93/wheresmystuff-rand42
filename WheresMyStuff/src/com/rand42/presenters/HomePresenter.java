package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.LocalModel;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/6/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomePresenter
{
    private IModel model;
    public HomePresenter(IModel model)
    {
        this.model=model;
    }

    /**
     * Logs out the user
     */
    public void logOut()
    {
      model.logOut();
    }

    /**
     * Gets the name of the current user
     * @return username
     */
    public String getUserName()
    {
        return model.getCurrentUser().getName();
    }
}

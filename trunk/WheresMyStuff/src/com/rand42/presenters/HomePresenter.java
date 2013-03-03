package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomePresenter
{
    private IModel model;
    public HomePresenter(IModel model)
    {
        this.model=model;
    }
    public Collection<Item> getUserItems()
    {
       return model.getUserItems(model.getUser());
    }
    public void logOut()
    {
        model.logOut();
    }
}

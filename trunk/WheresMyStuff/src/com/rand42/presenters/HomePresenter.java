package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.Requestor;
import com.rand42.views.interfaces.IHomeView;

import java.util.Collection;

/**
 * Presenter for the HomeView. Requests user item data and therefore implements Requestor
 * @author Rand-42
 */
public class HomePresenter implements Requestor<Item>
{
    private IModel model;
    private IHomeView view;
    public HomePresenter(IHomeView view, IModel model)
    {
        this.view=view;
        this.model=model;
    }

    /**
     * Makes the query to get the items associated with the current user
     */
    public void getUserItems()
    {
       model.getUserItems(model.getUser(), this);
    }

    /**
     * Called when the getUserItems query completes
     * @param items Query results
     */
    public void querySuccess(Collection<Item> items)
    {
        view.itemQuerySuccess(items);
    }

    /**
     * Logs out the user
     */
    public void logOut()
    {
        model.logOut();
    }
}

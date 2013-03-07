package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.Requestor;
import com.rand42.model.User;
import com.rand42.views.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Presenter for the HomeView. Requests user item data and therefore implements Requestor
 * @author Rand-42
 */
public class ItemListFragmentPresenter implements Requestor<Item>
{
    private IModel model;
    private IHomeView view;
   /* public static final int LOST_ITEMS=0;
    public static final int FOUND_ITEMS=1;
    public static final int ALL_ITEMS=2;
    pivate int currentFilter;*/
    public ItemListFragmentPresenter(IHomeView view, IModel model)
    {
        this.view=view;
        this.model=model;
    }

    /**
     * Makes the query to get the items associated with the current user
     */
    public void getUserItems()
    {
       // currentFilter = filter;
       model.getUserItems(model.getUser(), this);
    }
    public void deleteItem(Item item)
    {
        model.deleteItem(item,this);
    }

    /**
     * Called when the getUserItems query completes
     * @param items Query results
     */
    public void querySuccess(Collection<Item> items)
    {
        view.itemQuerySuccess(items);
    }

}

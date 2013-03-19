package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.Requestor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Presenter for the HomeView. Requests user item data and therefore implements Requestor
 * @author Rand-42
 */
public class ItemListFragmentPresenter
{
    private IModel model;
    public static final int LOST_ITEMS=0;
    public static final int FOUND_ITEMS=1;
    public static final int ALL_ITEMS=2;
    private int filter;
    
    public ItemListFragmentPresenter(IModel model, int filter)
    {
        this.model=model;
        this.filter=filter;
    }

    /**
     * Makes the query to get the items associated with the current user
     */
    public List<Item> getUserItems()
    {
       // currentFilter = filter;
       List<Item> items = model.getUserItems(model.getCurrentUser());
        List<Item> toRemove = new ArrayList();
        List<Item> filteredItems = new ArrayList<Item>();
        for(Item i:items)
        {
            if(filter==LOST_ITEMS)
                if(i.isLost())
                    filteredItems.add(i);
            if(filter==FOUND_ITEMS)
                if(!i.isLost())
                    filteredItems.add(i);
        }
        return filteredItems;

    }
    public boolean deleteItem(Item item)
    {
        return model.deleteItem(item);
    }


}

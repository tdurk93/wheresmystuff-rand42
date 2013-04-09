package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.ItemFilter;
import com.rand42.model.LocalModel;

import java.util.ArrayList;
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
        this.model= model;
        this.filter=filter;
    }

    /**
     * Makes the query to get the items associated with the current user
     * @return items associated with the current user
     */
    public List<Item> getUserItems()
    {
        if(model.getFilter()!=null)
            return getFilterItems();

       List<Item> items = model.getUserItems(model.getCurrentUser());
        List<Item> filteredItems = new ArrayList<Item>();

        for(Item i:items)
        {
            if(filter==LOST_ITEMS)
            {
                if(i.isLost())
                {
                    //if(itemFilter.filter(i))
                        filteredItems.add(i);
                }
            }

            if(filter==FOUND_ITEMS)
            {
                if(!i.isLost())
                {
                    //if(itemFilter.filter(i))
                        filteredItems.add(i);
                }
            }
        }
        return filteredItems;

    }
    
    /**
     * Makes the query to get the items that match a given filter
     * @return list of items that match a given filter
     */
    public List<Item> getFilterItems()
    {
        // currentFilter = filter;
        List<Item> items = model.getUserItems(model.getCurrentUser());
        List<Item> filteredItems = new ArrayList<Item>();
        ItemFilter itemFilter = model.getFilter();

        for(Item i:items)
        {
            if(filter==LOST_ITEMS)
            {
                if(i.isLost())
                {

                    if(itemFilter.filter(i))
                        filteredItems.add(i);
                }
            }

            if(filter==FOUND_ITEMS)
            {
                if(!i.isLost())
                {
                    if(itemFilter.filter(i))
                        filteredItems.add(i);
                }
            }
        }
        return filteredItems;

    }
    
   /**
    * Deletes an item in an item list 
    * @param item to delete
    * @return whether the item was deleted
    */
    public boolean deleteItem(Item item)
    {
        return model.deleteItem(item);
    }


}

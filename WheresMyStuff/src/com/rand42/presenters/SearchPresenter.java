package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/29/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchPresenter
{
    private final IModel model;
    
    /**
     * Creates a new search presenter for the model.
     * @param model
     */
    public SearchPresenter(IModel model)
    {
        this.model = model;
    }
    
    /**
     * Gets a list of items matching the query.
     * @param query
     * @return a list of matching items
     */
    public List<Item> searchItems(String query)
    {
       return model.searchItems(query);
    }
}

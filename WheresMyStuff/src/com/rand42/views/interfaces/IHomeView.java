package com.rand42.views.interfaces;

import com.rand42.model.Item;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/4/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IHomeView
{
    /**
     * Called when the getUserItem query completes. Populates list view
     * @param items Items results
     */
    public void itemQuerySuccess(Collection<Item> items);
}

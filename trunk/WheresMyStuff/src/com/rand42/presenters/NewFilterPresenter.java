package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.ItemFilter;
import com.rand42.model.LocalModel;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/2/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewFilterPresenter
{
    private final IModel model;

    public NewFilterPresenter()
    {
        model = LocalModel.getModel();
    }
    public void setFilter(ItemFilter filter)
    {
        model.setFilter(filter);
    }
    public ItemFilter getFilter()
    {
        return model.getFilter();
    }
}

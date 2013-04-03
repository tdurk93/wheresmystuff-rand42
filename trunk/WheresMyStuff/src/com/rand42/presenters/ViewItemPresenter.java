package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/2/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewItemPresenter
{
    private final IModel model;

    public ViewItemPresenter()
    {
        model = LocalModel.getModel();
    }

    public Item getItem(long id)
    {
        return model.getItemById(id);
    }
}

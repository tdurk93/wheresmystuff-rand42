package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.User;
import com.rand42.views.interfaces.INewItemView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewItemPresenter
{
    private final INewItemView view;
    private final IModel model;

    public NewItemPresenter(INewItemView view, IModel model)
    {
        this.view=view;
        this.model = model;
    }

    public void createItem(String name, String description, User owner, boolean lost)
    {
        model.createItem(name, description, owner,lost);
    }

}


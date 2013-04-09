package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.LocalModel;
import com.rand42.views.interfaces.INewItemView;

import java.util.Date;

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

    public NewItemPresenter(IModel model, INewItemView view)
    {
        this.view=view;
        this.model = model;
    }

    public void createItem(String name, String description, Date date, boolean lost, String category)
    {
        model.createItem(name, description, model.getCurrentUser(),date, lost, category);
    }

}


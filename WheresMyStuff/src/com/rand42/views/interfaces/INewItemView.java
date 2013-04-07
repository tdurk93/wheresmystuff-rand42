package com.rand42.views.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface INewItemView
{
    /**
     * Called when the creation of a new item is successful.
     */
    void createSuccess();
    
    /**
     * Called when the creation of a new item fails
     * @param message the failure message
     */
    void createFail(String message);

}

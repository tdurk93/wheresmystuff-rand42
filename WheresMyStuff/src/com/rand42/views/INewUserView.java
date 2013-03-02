package com.rand42.views;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface INewUserView
{
    /**
     * Called if creation was successful
     */
    public void createSuccess();

    /**
     * Called if creation failed
     * @param message Error message
     */
    public void createFail(String message);
}

package com.rand42.views.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILoginView
{
    /**
     * Called if login was successful
     * @param isAdmin
     */
    public void loginSuccess(boolean isAdmin);

    /**
     * Called if login failed
     * @param message
     */
    public void loginFail(String message);
}

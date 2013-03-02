package com.rand42.views;

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
     */
    public void loginSuccess();

    /**
     * Called if login failed
     */
    public void loginFail();
}

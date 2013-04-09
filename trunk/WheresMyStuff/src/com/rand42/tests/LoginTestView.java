package com.rand42.tests;

import com.rand42.views.interfaces.ILoginView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/8/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginTestView implements ILoginView
{
    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }

    boolean success;
    String message="";
    @Override
    public void loginSuccess(boolean isAdmin)
    {
        success=true;
    }

    @Override
    public void loginFail(String message)
    {
        success=false;
        this.message = message;
    }

}

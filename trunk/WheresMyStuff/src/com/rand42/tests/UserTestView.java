package com.rand42.tests;

import com.rand42.views.interfaces.INewUserView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/14/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTestView implements INewUserView
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
    public void createSuccess()
    {
        success=true;
    }

    @Override
    public void createFail(String message)
    {
        success=false;
        this.message=message;
    }
}

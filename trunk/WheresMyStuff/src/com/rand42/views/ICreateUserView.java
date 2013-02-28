package com.rand42.views;

import com.rand42.presenters.CreateUserPresenter;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/28/13
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICreateUserView
{
    public void success();
    public void fail(CreateUserPresenter.CreateUserFail fail);
}

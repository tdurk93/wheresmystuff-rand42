package com.rand42.tests;

import com.rand42.presenters.LoginPresenter;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/7/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPresenterTest
{
    LoginTestView view = new LoginTestView();
    LoginPresenter presenter = new LoginPresenter(new LoginTestModel(), view);
    //Locked out user
    @Test(timeout=200)
    public void testA()
    {

        presenter.logIn("attempts","locked");
        assertTrue(!view.isSuccess()&&view.getMessage().equals("You have attempted to login too many times. Try again later"));

    }
    //bad login
    @Test(timeout = 200)
    public void testB()
    {
        presenter.logIn("incorrect","incorrect");
        assertTrue(!view.isSuccess()&&view.getMessage().equals("Invalid Credentials"));

    }
    //locked user
    @Test(timeout = 200)
    public void testC()
    {
        presenter.logIn("locked","locked");
        assertTrue(!view.isSuccess()&&view.getMessage().equals("Your account has been locked"));

    }
    @Test(timeout = 200)
    public void TestD()
    {
        presenter.logIn("correct","correct");
        assertTrue(view.isSuccess());
    }
}

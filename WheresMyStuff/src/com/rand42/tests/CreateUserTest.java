package com.rand42.tests;

import com.rand42.presenters.NewUserPresenter;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/14/13
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateUserTest
{

    UserTestView view = new UserTestView();
    NewUserPresenter presenter = new NewUserPresenter(new LoginTestModel(), view );
    @Test(timeout = 200)
    public void testA()
    {
        presenter.createUser("email@email.com","alex","password","otherpassword", false);
        assertTrue(view.getMessage().equals("Passwords don't match"));
    }
    @Test(timeout = 200)
    public void testB()
    {
        presenter.createUser("email@email.com","alex","","", false);
        assertTrue(view.getMessage().equals("You must complete the password field"));
    }
    @Test(timeout = 200)
    public void testC()
    {
        presenter.createUser("email@email.com","alex","password","password", false);
        assertTrue(view.isSuccess());
    }
    @Test(timeout = 200)
    public void testD()
    {
        presenter.createUser("exists@exists.com","alex","password","password", false);
        assertTrue(view.getMessage().equals("Username already exists"));
    }



}

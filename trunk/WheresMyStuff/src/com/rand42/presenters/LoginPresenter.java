package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.views.interfaces.ILoginView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPresenter
{
    private final ILoginView view;
    private final IModel model;

    /**
     * Creates a new presenter with view and model
     * @param view Associated view
* @param model  Associated model
     */
    public LoginPresenter(ILoginView view, IModel model)
    {
        this.view=view;
        this.model=model;
    }

    /**
     * Logs in a user
     * @param email  email
     * @param password password
     */
    public void logIn(final String email, String password)
    {
        if(model.checkUserAttempts(email))
        {
            //TODO: MOVE OUT
           if(model.logIn(email, password))
           {
               if(!model.getCurrentUser().isActive())
                   view.loginFail("Your account has been locked");
               else
                   view.loginSuccess(model.getCurrentUser().isAdmin());
           }
           else
               view.loginFail("Invalid Creds");
        }
        else
            view.loginFail("You have attempted to login too many times. Try again later");
    }


}

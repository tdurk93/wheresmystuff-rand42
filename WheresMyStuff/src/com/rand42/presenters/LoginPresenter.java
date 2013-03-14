package com.rand42.presenters;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rand42.model.DatabaseHandler;
import com.rand42.model.IModel;
import com.rand42.model.User;
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
            DatabaseHandler dbh = DatabaseHandler.getHandler();
            model.logIn(email, password, new LogInCallback(){
                public void done(ParseUser u, ParseException e)
                {
                    if(u!=null)
                    {
                        User user = new User(u);
                        if(!model.isUserLocked(user))
                        {
                            model.setCurrentUser(new User(u));
                            view.loginSuccess(model.getCurrentUser().isAdmin());
                            model.resetAttempts(email);
                        }
                       else if(model.isUserQueued(user))
                        {
                            model.performUserDelete(user);
                            view.loginFail("Your account has been deleted");
                        }
                        else
                        {
                            view.loginFail("Your account has been locked");
                            model.setCurrentUser(null);
                        }

                    }
                    else
                    {
                        view.loginFail(e.getMessage());
                        model.setCurrentUser(null);
                    }

                }
            });
        }
        else
            view.loginFail("You have attempted to login too many times. Try again later");
    }


}

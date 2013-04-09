package com.rand42.presenters;

import com.rand42.model.IModel;
import com.rand42.model.LocalModel;
import com.rand42.views.interfaces.INewUserView;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewUserPresenter
{
    private final INewUserView view;
    private final IModel model;

    private static final String EMAIL_PATTERN =
           "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern;

    /**
     * Creates a new presenter
     * @param view Associated view

     */
    public NewUserPresenter(IModel model,INewUserView view )
    {
       this.view=view;
       this.model= model;
       pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Creates a new user. Validates info first
     * @param email email
     * @param name name
     * @param password password
     * @param confirm  confirm
     */
    public void createUser(String email, String name, String password, String confirm, boolean isAdmin)
    {
        String errorMsg;
        if(!(errorMsg=verify(email, password, confirm)).equals(""))
        {
            view.createFail(errorMsg);
        }
        else if(password.length()==0)
        {
            view.createFail("You must complete the password field");
        }
        else
        {
            if(model.addUser(email, name, password, isAdmin))
            {
                view.createSuccess();
            }
            else
                view.createFail("Username already exists");
        }

    }
    private String verify(String email, String password, String confirm){


        String errorMsg = "";

        if(!pattern.matcher(email).matches())
            errorMsg = "Email misformatted";

        if(!password.equals(confirm)) 
            errorMsg = "Passwords don't match";
        return errorMsg;

    }
}

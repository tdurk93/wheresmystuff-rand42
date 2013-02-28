package com.rand42.presenters;

import com.parse.ParseException;
import com.parse.SignUpCallback;
import com.rand42.model.DatabaseHandler;
import com.rand42.views.ICreateUserView;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/28/13
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateUserPresenter
{
    private final ICreateUserView createView;
    public enum CreateUserFail {invalidData, timeout, exists, unknown}
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern;

    public CreateUserPresenter(ICreateUserView view)
    {
        createView = view;
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void createUserClick(String name, String email, String password, String confirm)
    {
        if(pattern.matcher(email).matches()&&password.equals(confirm))
        {
            DatabaseHandler db = DatabaseHandler.getHandler();
            db.createUser(name, email,password, new SignUpCallback()
            {
                @Override
                public void done(ParseException e)
                {
                    if(e==null)
                        createView.success();
                    else
                    {

                        if(e.getCode()==ParseException.USERNAME_TAKEN)
                        createView.fail(CreateUserFail.exists);
                        else if(e.getCode()==ParseException.TIMEOUT)
                            createView.fail(CreateUserFail.timeout);
                        else
                            createView.fail(CreateUserFail.unknown);
                    }
                }
            });
        }
        else
            createView.fail(CreateUserFail.invalidData);
    }

}

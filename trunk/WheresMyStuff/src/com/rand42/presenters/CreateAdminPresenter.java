package com.rand42.presenters;

import java.util.List;
import java.util.regex.Pattern;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SignUpCallback;
import com.rand42.model.IModel;
import com.rand42.views.interfaces.ICreateAdminView;

public class CreateAdminPresenter {

    private final ICreateAdminView view;
    private final IModel model;

    private static final String EMAIL_PATTERN =
           "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern;

    /**
     * Creates a new presenter
     * @param view Associated view
     * @param model Associated model
     */
    public CreateAdminPresenter(ICreateAdminView view, IModel model){
       this.view=view;
       this.model=model;
       pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Creates a new user. Validates info first
     * @param email email
     * @param name name
     * @param password password
     * @param confirm  confirm
     */
    
    public void createAdmin(String email, String name, String password, String confirm){
        String errorMsg;
        if((errorMsg=verify(email, password, confirm))!="")
        	view.createFail(errorMsg);
        else
            model.addUser(email, name, password, true, new SignUpCallback()
            {
               public void done(ParseException e)
               {
                    if(e==null)
                        view.createSuccess();
                   else
                        view.createFail(e.getMessage());

               }
            });
    }

    public void promoteToAdmin(String email){
    	model.promoteUser(email, new FindCallback(){

			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				
			} 
			});
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

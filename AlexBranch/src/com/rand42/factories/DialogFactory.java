package com.rand42.factories;

import com.parse.ParseException;
import com.parse.RequestPasswordResetCallback;
import com.rand42.model.DatabaseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Class with methods to aid in the creation of Dialog popups
 * @author Rand-42
 *
 */
public class DialogFactory {
	
	/**
	 * Creates a dialog with a message and a title
	 * @param title Title
	 * @param message Message to display
	 * @param caller The calling activity
	 * @return The created dialog
	 */
	public static AlertDialog createStandardDialog(String title, String message, Activity caller)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(caller);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setPositiveButton("Ok",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog,
                             int which) {
                         dialog.dismiss();
                     }
                 });
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setCancelable(false);
		return alertDialogBuilder.create();
	}
	/**
	 * Creates a dialog designed to quit the current activity. Shows the message and quits the activity after ok is clicked
	 * @param title Title
	 * @param message Message 
	 * @param caller Calling activity. Will be closed
	 * @return Created dialog box
	 */
	public static AlertDialog createFinishDialog(String title, String message, Activity caller)
	{
		final Activity theCaller = caller;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(caller);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setPositiveButton("Ok",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog,
                             int which) {
                         dialog.dismiss();
                         theCaller.finish();
                     }
                 });
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setCancelable(false);
		return alertDialogBuilder.create();
	}
	/**
	 * Creates a dialog to aid in password reset. Contains an input box, resets user passwords, and creates a message dialog on close
	 * @param c The calling activity
	 * @return The created dialog
	 */
	public static AlertDialog createResetDialog(Activity c)
	{
		final Activity caller = c;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(caller);
		final EditText input = new EditText(caller);
		alertDialogBuilder.setView(input);
		alertDialogBuilder.setTitle("Enter Email");
		alertDialogBuilder.setPositiveButton("Ok",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog,
                             int which) {
                    	 DatabaseHandler db = DatabaseHandler.getHandler();
                    	 dialog.dismiss();
                    	 db.resetPassword(input.getText().toString(), new RequestPasswordResetCallback()
                    	 {
                    		 public void done(ParseException e)
                    		 {
                    			 if(e==null)
                    			 {
                    				 AlertDialog infoDialog = DialogFactory.createStandardDialog("","You will recieve an email shortly",caller);
                                	 infoDialog.show();
                                     
                    			 }
                    			 else
                    			 {
                    				 AlertDialog infoDialog = DialogFactory.createStandardDialog("","A problem occured",caller);
                                	 infoDialog.show();
                    			 }
                    		 }
                    	 });
                    	
                     }
                 });
		alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                            int which) {
                  
                        dialog.dismiss();
                    }
                });
		alertDialogBuilder.setMessage("Enter your email");
		alertDialogBuilder.setCancelable(true);
		return alertDialogBuilder.create();
	}

}

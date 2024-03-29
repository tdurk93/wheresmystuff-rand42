package com.rand42.views;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.rand42.factories.DialogFactory;
import com.rand42.model.LocalModel;
import com.rand42.model.User;
import com.rand42.presenters.ViewUserPresenter;
import com.rand42.views.interfaces.IViewUserView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewUserActivity extends RoboActivity implements IViewUserView, CompoundButton.OnCheckedChangeListener
{
    @InjectView(R.id.nameView) TextView nameView;
    @InjectView(R.id.emailView) TextView emailView;
    @InjectView(R.id.adminView) TextView adminView;
    @InjectView(R.id.lockSwitch) private Switch lockSwitch;

    private User currentUser;
    private ViewUserPresenter presenter;
    
    /**
     * Called when a ViewUserActivity is created
     * @param savedInstanceState a previously saved state, if applicable
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        presenter = new ViewUserPresenter(LocalModel.getModel(),this);
        long id = getIntent().getExtras().getLong("id");
        loadUser(id);

    }
    
    /**
     * Load a user based on their user id and set the action bar text 
     * based on whether or not the specified user is an admin
     * @param id 
     */
    private void loadUser(long id)
    {
        User u = presenter.loadUser(id);
        currentUser = u;
        nameView.setText(u.getName());
        emailView.setText(u.getEmail());
        adminView.setText(u.isAdmin()?"Administrator":"Standard User");
        lockSwitch.setChecked(u.isActive());
        lockSwitch.setOnCheckedChangeListener(this);

        getActionBar().setTitle(u.getName());
    }
    
    /**
     * Called when the delete user button is pressed
     * @param view the View context
     */
    public void deleteClick(View view)
    {
        if(presenter.deleteUser(currentUser));
        DialogFactory.createFinishDialog("Success", "User deleted", this).show();
    }
    
    /**
     * Called when the "lock user" checkbox state changes
     * @param compoundButton the Checkbox
     * @param b whether the checkbox is now checked or unchecked.
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        if(b)
            presenter.lockUser(currentUser);
        else
            presenter.unlockUser(currentUser);
        Toast toast = Toast.makeText(getApplicationContext(), emailView.getText()+" "+(b?"locked":"unlocked"), Toast.LENGTH_SHORT);
        toast.show();
    }
}
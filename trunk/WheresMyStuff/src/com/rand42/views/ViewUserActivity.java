package com.rand42.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.rand42.model.LocalModel;
import com.rand42.model.User;
import com.rand42.presenters.ViewUserPresenter;
import com.rand42.views.interfaces.IViewUserView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewUserActivity extends Activity implements IViewUserView, CompoundButton.OnCheckedChangeListener
{
    private TextView nameView,emailView,adminView;
    private Switch lockSwitch;
    private User currentUser;
    private ViewUserPresenter presenter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        nameView = (TextView)findViewById(R.id.nameView);
        emailView = (TextView)findViewById(R.id.emailView);
        adminView = (TextView)findViewById(R.id.adminView);
        lockSwitch = (Switch)findViewById(R.id.lockSwitch);


        presenter = new ViewUserPresenter(LocalModel.getModel(), this);
        String email = getIntent().getStringExtra("email");
        loadData(email);
    }

    private void loadData(String email)
    {
       presenter.loadUser(email);
    }


    @Override
    public void presentData(User u, boolean userLocked)
    {
        currentUser = u;
        nameView.setText(u.getName());
        emailView.setText(u.getEmail());
        adminView.setText(u.isAdmin()?"Administrator":"Standard User");
        lockSwitch.setChecked(userLocked);
        lockSwitch.setOnCheckedChangeListener(this);
    }
    public void deleteClick(View view)
    {
        presenter.deleteUser(currentUser);
        this.finish();
    }

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
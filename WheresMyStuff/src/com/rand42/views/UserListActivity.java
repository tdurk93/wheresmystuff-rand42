package com.rand42.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rand42.model.LocalModel;
import com.rand42.model.User;
import com.rand42.presenters.UserListPresenter;
import com.rand42.views.interfaces.IUserListView;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/13/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserListActivity extends Activity implements IUserListView, AdapterView.OnItemClickListener
{
    private ListView list;
    private UserListPresenter presenter;
    private ArrayAdapter<User> adapter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        presenter = new UserListPresenter(LocalModel.getModel(), this);
        list = (ListView)findViewById(R.id.userListView);
        list.setOnItemClickListener(this);
        adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
    }
    public void onStart()
    {
        super.onStart();
        populateList();
    }


    public void populateList()
    {
        presenter.getUsers();
    }
    @Override
    public void userQuerySuccess(Collection<User> users)
    {
        if(users!=null)
        {

            User[] usersArr = Arrays.copyOf(users.toArray(), users.toArray().length, User[].class);
            adapter.clear();
            adapter.addAll(usersArr);
            adapter.notifyDataSetChanged();
            list.setAdapter(adapter);
        }
        //progressDialog.hide();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
       User clicked =  (User)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, ViewUserActivity.class);
       intent.putExtra("email",clicked.getEmail());
        startActivity(intent);
    }
}
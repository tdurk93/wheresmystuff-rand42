package com.rand42.views;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.rand42.presenters.HomePresenter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Activity representing the home page for the logged in user
 * @author Rand-42
 *
 */
public class HomeActivity extends Activity implements AdapterView.OnItemClickListener
{

	private HomePresenter presenter;
    private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		presenter = new HomePresenter(LocalModel.getModel());
        list = (ListView)findViewById(R.id.itemlist);
        list.setOnItemClickListener(this);
	}
    @Override
    protected void onStart()
    {
        super.onStart();
       populateList();
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
    public void populateList()
    {
        Collection<Item> items = presenter.getUserItems();
        if(items!=null)
        {

            Item[] itemsArr = Arrays.copyOf(items.toArray(), items.toArray().length, Item[].class);
            ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, android.R.id.text1,itemsArr);
            list.setAdapter(adapter);
        }
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//TODO: Stefano really doesn't like looking at this.
		switch(item.getItemId())
		{
		case R.id.menu_logout:
			presenter.logOut();
			this.finish();
			return true;
        case R.id.menu_addItem:
            Intent i = new Intent(this, NewItemActivity.class);
            startActivity(i);
            //populateList();
            return true;
		default:
			return true;
		}
	}
	@Override
	public void onBackPressed()
	{
	    presenter.logOut();
		this.finish();
	}


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Item item = (Item)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, ViewItemActivity.class);
        intent.putExtra("UID", item.getUID());
        startActivity(intent);
    }
}

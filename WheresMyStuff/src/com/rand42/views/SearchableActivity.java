package com.rand42.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;
import com.rand42.presenters.SearchPresenter;
import com.rand42.views.adapters.ItemAdapter;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/29/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchableActivity extends RoboActivity implements AdapterView.OnItemClickListener
{
    SearchPresenter presenter;
    @InjectView(R.id.itemSearchlist) private ListView resultsView;
    
    /**
     * Called when a SearchingActivity is created
     * @param savedInstanceState a previous state, if applicable
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenter(LocalModel.getModel());
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra("query");
            search(query);
        }

        resultsView.setOnItemClickListener(this);
        getActionBar().setTitle("Results");
    }
    
    /**
     * Used the specified search query to search through the items
     * @param query The text String to search for
     */
    public void search(String query)
    {
        List<Item> items = presenter.searchItems(query);
        ItemAdapter adapter = new ItemAdapter(this, R.layout.item_list_row, Arrays.copyOf(items.toArray(), items.toArray().length, Item[].class));
        resultsView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    
    /**
     * Called when an item in the searched list is pressed
     * @param adapterView
     * @param view
     * @param i
     * @param l 
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Item item = (Item)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, ViewItemActivity.class);
        intent.putExtra("item", item.getID());
        startActivity(intent);
    }
}
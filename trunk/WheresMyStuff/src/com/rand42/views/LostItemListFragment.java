package com.rand42.views;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;
import com.rand42.presenters.HomePresenter;
import com.rand42.views.interfaces.IHomeView;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/6/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class LostItemListFragment extends Fragment implements IHomeView, AdapterView.OnItemClickListener
{
    private ListView list;
    private HomePresenter presenter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_lost_item_list,  container, false);
        list = (ListView)view.findViewById(R.id.itemlist);
        list.setOnItemClickListener(this);
        presenter = new HomePresenter(this, LocalModel.getModel());
        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        populateList();
    }


    public void populateList()
    {
        presenter.getUserItems(HomePresenter.FOUND_ITEMS);
    }


    @Override
    public void itemQuerySuccess(Collection<Item> items)
    {
        if(items!=null)
        {

            Item[] itemsArr = Arrays.copyOf(items.toArray(), items.toArray().length, Item[].class);
            ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1,itemsArr);
            list.setAdapter(adapter);
        }
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Item item = (Item)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this.getActivity(), ViewItemActivity.class);
        intent.putExtra("UID", item.getUID());
        startActivity(intent);
    }

}

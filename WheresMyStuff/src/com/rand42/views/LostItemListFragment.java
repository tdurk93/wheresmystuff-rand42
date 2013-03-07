package com.rand42.views;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rand42.factories.DialogFactory;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;
import com.rand42.presenters.ItemListFragmentPresenter;
import com.rand42.views.interfaces.IHomeView;

import java.util.Arrays;
import java.util.Collection;

/**
 * A Fragment to view Items in a list. Can populate itself
 */
public class LostItemListFragment extends Fragment implements IHomeView, AdapterView.OnItemClickListener
{
    private ListView list;
    private ItemListFragmentPresenter presenter;
    private ArrayAdapter<Item> adapter;
    private AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_lost_item_list,  container, false);
        list = (ListView)view.findViewById(R.id.itemlist);
        list.setOnItemClickListener(this);
        registerForContextMenu(list);
        presenter = new ItemListFragmentPresenter(this, LocalModel.getModel());
        adapter = new ArrayAdapter<Item>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1);
        progressDialog = DialogFactory.createIndeterminateProgressDialog("Delete","Deleting", this.getActivity());
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        populateList();
    }

    /**
     * Starts a query to populate the list in this fragment
     */
    public void populateList()
    {
        presenter.getUserItems();
    }


    @Override
    public void itemQuerySuccess(Collection<Item> items)
    {
        if(items!=null)
        {

            Item[] itemsArr = Arrays.copyOf(items.toArray(), items.toArray().length, Item[].class);
            adapter.clear();
            adapter.addAll(itemsArr);
            adapter.notifyDataSetChanged();
            list.setAdapter(adapter);
        }
        progressDialog.hide();
    }

    /**
     * Navigates to the detail view of the clicked item
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Item item = (Item)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this.getActivity(), ViewItemActivity.class);
        intent.putExtra("UID", item.getUID());
        startActivity(intent);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

        Item selectedItem = adapter.getItem(info.position);
        menu.setHeaderTitle(selectedItem.getName());
        menu.add(Menu.NONE,0,Menu.NONE, "Edit" );
        menu.add(Menu.NONE,1,Menu.NONE, "Delete" );
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Item selectedItem = adapter.getItem(info.position);
        switch(item.getItemId())
        {
            case 0:
                //edit;
                break;
            case 1:
                presenter.deleteItem(selectedItem);
                progressDialog.show();
                break;
        }

        return true;


    }

}

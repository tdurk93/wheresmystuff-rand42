package com.rand42.views;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.rand42.factories.DialogFactory;
import com.rand42.model.Item;
import com.rand42.presenters.ItemListFragmentPresenter;
import com.rand42.views.adapters.ItemAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * A Fragment to view Items in a list. Can populate itself
 */
public class ItemListFragment extends Fragment implements  AdapterView.OnItemClickListener
{
    private ListView list;
    private ItemListFragmentPresenter presenter;
    private ItemAdapter adapter;

    //private int filter;

    public static ItemListFragment newInstance(int filter)
    {
        ItemListFragment fragment = new ItemListFragment();
        Bundle bdl = new Bundle(1);
        bdl.putInt("filter",filter);
        fragment.setArguments(bdl);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_lost_item_list,  container, false);
        list = (ListView)view.findViewById(R.id.itemlist);
        list.setOnItemClickListener(this);
        registerForContextMenu(list);
        presenter = new ItemListFragmentPresenter(getArguments().getInt("filter"));
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
       List<Item> items =  presenter.getUserItems();
        if(items!=null)
        {

            Item[] itemsArr = Arrays.copyOf(items.toArray(), items.toArray().length, Item[].class);
            adapter = new ItemAdapter(this.getActivity(), R.layout.item_list_row, itemsArr);
            adapter.notifyDataSetChanged();
            list.setAdapter(adapter);
        }
        //progressDialog.hide();
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
        intent.putExtra("item", item.getID());
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
    	
    	//TODO: Fix this shit.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Item selectedItem = adapter.getItem(info.position);
        switch(item.getItemId())
        {
            case 0:
                //edit;
                break;
            case 1:
               if(presenter.deleteItem(selectedItem));
            {
                DialogFactory.createStandardDialog("Success", "Item deleted", getActivity()).show();
                populateList();
            }

                break;
        }

        return true;


    }

}

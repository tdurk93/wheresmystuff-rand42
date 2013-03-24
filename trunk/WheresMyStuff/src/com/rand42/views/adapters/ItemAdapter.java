package com.rand42.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rand42.model.Item;
import com.rand42.views.R;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/22/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemAdapter extends ArrayAdapter<Item>
{
    Context context;
    int layoutResourceId;
    Item data[] = null;

    public ItemAdapter(Context context, int textViewResourceId, Item[] objects)
    {
        super(context, textViewResourceId, objects);
        this.layoutResourceId = textViewResourceId;
        this.data=objects.clone();
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
       View row = convertView;
        ItemHolder holder;
       if(row==null)
       {
           LayoutInflater inflater = ((Activity)context).getLayoutInflater();
           row = inflater.inflate(layoutResourceId,  parent, false);
           holder = new ItemHolder();
           holder.titleView = (TextView)row.findViewById(R.id.itemTitle);
           holder.detailView = (TextView)row.findViewById(R.id.itemDetail1);
           row.setTag(holder);

       }
        else
        {
            holder = (ItemHolder)row.getTag();
        }
        Item item = data[position];
        holder.titleView.setText(item.getName());
        String detailString="";
        detailString+=item.getDate().toString();
        detailString +=" "+item.getCategory();
        holder.detailView.setText(detailString);

        return row;
    }
    static class ItemHolder
    {
        TextView titleView;
        TextView detailView;
    }
}

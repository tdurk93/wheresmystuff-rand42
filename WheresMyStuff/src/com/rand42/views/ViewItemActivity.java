package com.rand42.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.rand42.model.Item;
import com.rand42.model.LocalModel;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewItemActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        Bundle bundle = getIntent().getExtras();
        long id = bundle.getLong("item");
        Item i = LocalModel.getModel().getItemById(id);
        TextView uidView = (TextView)findViewById(R.id.textView);
        TextView detailView = (TextView)findViewById(R.id.textView1);
        uidView.setText(i.getName());
        detailView.setText(i.getCategory());

    }
}
package com.rand42.views;

import android.os.Bundle;
import android.widget.TextView;
import com.rand42.model.Item;
import com.rand42.presenters.ViewItemPresenter;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewItemActivity extends RoboActivity
{
    @InjectView(R.id.textView)  TextView nameView;
    @InjectView(R.id.textView1) TextView detailView;
    @InjectView(R.id.textView2) TextView descView;
    ViewItemPresenter presenter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        Bundle bundle = getIntent().getExtras();
        long id = bundle.getLong("item");
        presenter = new ViewItemPresenter();
        Item i = presenter.getItem(id);

        nameView.setText(i.getName());
        detailView.setText(i.getCategory());
        descView.setText(i.getDescription());

        getActionBar().setTitle(i.getName());

    }
}
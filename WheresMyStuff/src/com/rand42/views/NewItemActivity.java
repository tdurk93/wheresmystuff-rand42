package com.rand42.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import com.rand42.model.LocalModel;
import com.rand42.presenters.NewItemPresenter;
import com.rand42.views.interfaces.INewItemView;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewItemActivity extends Activity implements INewItemView
{
    private NewItemPresenter presenter;
    private EditText nameField, descField;
    private Switch lostSwitch;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        presenter = new NewItemPresenter(this, LocalModel.getModel());
        nameField = (EditText)findViewById(R.id.itemNameField);
        descField = (EditText)findViewById(R.id.itemDescField);
        lostSwitch = (Switch)findViewById(R.id.lostSwitch);

    }

    public void createItem(View view)
    {
        presenter.createItem(nameField.getText().toString(), descField.getText().toString(), LocalModel.getModel().getUser(), lostSwitch.isChecked());
        this.finish();
    }
    @Override
    public void createSuccess()
    {

    }

    @Override
    public void createFail(String message)
    {

    }
}
package com.rand42.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.rand42.model.LocalModel;
import com.rand42.presenters.NewItemPresenter;
import com.rand42.views.interfaces.INewItemView;

import java.util.Calendar;
import java.util.Date;

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
    private Date itemDate;
    private TextView dateView;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        presenter = new NewItemPresenter(this, LocalModel.getModel());
        nameField = (EditText)findViewById(R.id.itemNameField);
        descField = (EditText)findViewById(R.id.itemDescField);
        lostSwitch = (Switch)findViewById(R.id.lostSwitch);
        dateView = (TextView)findViewById(R.id.dateTextView);
        itemDate = new Date();
        Calendar c = Calendar.getInstance();
        setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

    }

    public void createItem(View view)
    {
        presenter.createItem(nameField.getText().toString(), descField.getText().toString(), LocalModel.getModel().getCurrentUser(),itemDate, lostSwitch.isChecked());
        this.finish();
    }
    public void dateClicked(View view)
    {
       Calendar c = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3)
            {
                setDate(i,i2,i3);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

        dpd.show();

    }

    private void setDate(int y, int m, int d)
    {
        Calendar c = Calendar.getInstance();
        c.set(y,m,d);
        itemDate.setTime(c.getTimeInMillis());
        dateView.setText((m+1)+"/"+d+"/"+y); //months are apparently zero indexed


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
package com.rand42.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
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
    private RadioButton lostButton;
    private Date itemDate;
    private TextView dateView;
    private Spinner categorySpinner;
    String[] categories = {"Keepsake", "Valuable", "Vital", "Others"};

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        presenter = new NewItemPresenter(this, LocalModel.getModel());
        nameField = (EditText)findViewById(R.id.itemNameField);
        descField = (EditText)findViewById(R.id.itemDescField);
        lostButton = (RadioButton)findViewById(R.id.lostRadioButton);
        dateView = (TextView)findViewById(R.id.dateTextView);
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        setupSpinner();

        itemDate = new Date();
        Calendar c = Calendar.getInstance();
        setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

        getActionBar().setTitle("New Item");

    }

    private void setupSpinner()
    {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
    }

    public void createItem(View view)
    {
        String category = (String)categorySpinner.getSelectedItem();
        presenter.createItem(nameField.getText().toString(), descField.getText().toString(), LocalModel.getModel().getCurrentUser(),itemDate, lostButton.isChecked(),category);
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
        c.set(y,m,d,0,0,0);
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
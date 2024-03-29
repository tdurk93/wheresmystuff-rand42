package com.rand42.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.rand42.model.LocalModel;
import com.rand42.presenters.NewItemPresenter;
import com.rand42.views.interfaces.INewItemView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewItemActivity extends RoboActivity implements INewItemView
{
    private NewItemPresenter presenter;
    @InjectView(R.id.itemNameField) private EditText nameField, descField;
    private RadioButton lostButton;
    private Date itemDate;
    private TextView dateView;
    private Spinner categorySpinner;
    String[] categories = {"Keepsake", "Valuable", "Vital", "Others"};

    /**
     * Called when this Activity is created
     * @param savedInstanceState a previously saved state, if applicable
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        presenter = new NewItemPresenter(LocalModel.getModel(),this);
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

    /**
     * Sets up the spinner to select the category of the item
     */
    private void setupSpinner()
    {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
    }
    
    /**
     * Called to create the item based on the parameters selected by the user
     * @param view the View context
     */
    public void createItem(View view)
    {
        String category = (String)categorySpinner.getSelectedItem();
        presenter.createItem(nameField.getText().toString(), descField.getText().toString(), itemDate, lostButton.isChecked(),category);
        this.finish();
    }
    
    /**
     * Shows a calendar when the "Date" button is pressed
     * @param view the View context
     */
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
    
    /**
     * Sets the date based on the year/month/day specified
     * @param y year (4 digits)
     * @param m month
     * @param d day
     */
    private void setDate(int y, int m, int d)
    {
        Calendar c = Calendar.getInstance();
        c.set(y,m,d,0,0,0);
        itemDate.setTime(c.getTimeInMillis());
        dateView.setText((m+1)+"/"+d+"/"+y); //months are apparently zero indexed


    }
    
    /**
     * Currently not supported
     */
    @Override
    public void createSuccess()
    {

    }
    
    /**
     * Currently not supported
     * @param message 
     */
    @Override
    public void createFail(String message)
    {

    }
}
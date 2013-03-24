package com.rand42.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.rand42.model.ItemFilter;
import com.rand42.model.LocalModel;
import com.rand42.views.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/23/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewFilterActivity extends Activity
{
    RadioButton beforeButton, afterButton;
    Date date;
    TextView dateView;
    Spinner spinner;
    Switch dateSwitch, categorySwitch;
    List<String> categories;
    ArrayAdapter<String> spinnerAdapter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_filter);
        beforeButton = (RadioButton)findViewById(R.id.beforeRadioButton);
        afterButton = (RadioButton)findViewById(R.id.afterRadioButton);
        spinner = (Spinner)findViewById(R.id.spinner);
        dateView = (TextView)findViewById(R.id.dateFilterView);
        dateSwitch = (Switch)findViewById(R.id.dateFilterSwitch);
        categorySwitch = (Switch)findViewById(R.id.categoryFilterSwitch);
        date=new Date();
        Calendar c = Calendar.getInstance();
        setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));


        setupSpinner();
        setupFilter();
    }

    private void setupFilter()
    {
        ItemFilter currentFilter = LocalModel.getModel().getFilter();
        if(currentFilter!=null)
        {
            if(currentFilter.isDateFilter())
            {
                dateSwitch.setChecked(true);
                setDate(currentFilter.getDateFilterTime());
                beforeButton.setChecked(currentFilter.getDateBefore());
                afterButton.setChecked(!currentFilter.getDateBefore());
            }
            if(currentFilter.isCategoryFilter())
            {
                categorySwitch.setChecked(true);
                spinner.setSelection(categories.indexOf(currentFilter.getCategoryFiterString()));
            }
        }

    }

    private void setupSpinner()
    {
        categories = new ArrayList<String>();
        categories.add("Keepsake");
        categories.add("Vital");
        categories.add("Valuable");
        categories.add("Other");
        spinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void createFilter(View view)
    {
        ItemFilter itemFilter = new ItemFilter();
        if(dateSwitch.isChecked())
            itemFilter.enableDateFilter(date.getTime(), beforeButton.isChecked());
        if(categorySwitch.isChecked())
            itemFilter.enableCategoryFilter((String)spinner.getSelectedItem());
        LocalModel.getModel().setFilter(itemFilter);
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
        date.setTime(c.getTimeInMillis());
        dateView.setText((m + 1) + "/" + d + "/" + y); //months are apparently zero indexed


    }
    private void setDate(long seconds)
    {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(seconds);
        setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
    }
}
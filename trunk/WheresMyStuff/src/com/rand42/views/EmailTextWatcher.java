package com.rand42.views;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * For use with EditText. Checks for valid email strings
 */
public class EmailTextWatcher implements TextWatcher
{
    EditText field;
    private static final String EMAIL_PATTERN =
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern;
    
    /**
     * Initializes an EmailTextWatcher with the specified field
     * @param field the field to watch text in
     */
    public EmailTextWatcher(EditText field)
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
        this.field=field;
    }
    
    /**
     * Method not supported
     * @param charSequence
     * @param i
     * @param i2
     * @param i3 
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    /**
     * Checks to make sure that an email is valid. If not, sets the error on the EditText
     * @param charSequence the new text of the field, represented as a
     * CharSequence object.
     * @param i the "start" of the text being being changed
     * @param i2 the length of the old text that has been replaced.
     * @param i3 the number of characters that have been replaced.
     */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
    {

        if(!pattern.matcher(charSequence).matches())
            field.setError("Invalid Email");
    }
    
    /**
     * Method not supported
     * @param editable the text field, as an Editable object
     */
    @Override
    public void afterTextChanged(Editable editable)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

package com.rand42.views;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/3/13
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailTextWatcher implements TextWatcher
{
    EditText field;
    private static final String EMAIL_PATTERN =
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern;

    public EmailTextWatcher(EditText field)
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
        this.field=field;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
    {

        if(!pattern.matcher(charSequence).matches())
            field.setError("Invalid Email");
    }

    @Override
    public void afterTextChanged(Editable editable)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

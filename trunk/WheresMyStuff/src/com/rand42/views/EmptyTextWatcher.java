package com.rand42.views;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EmptyTextWatcher implements TextWatcher
{
        EditText field;

       public EmptyTextWatcher(EditText field)
        {
            this.field = field;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {
            if (charSequence.toString().length() == 0)
                field.setError("This must be completed");

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

}
package com.rand42.views;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * For use with EditText fields. Checks for empty EditText fields.
 */
public class EmptyTextWatcher implements TextWatcher
{
        EditText field;
        
        /**
         * Constructor - initializes the EmptyTextWatcher with the specified field
         * @param field 
         */
       public EmptyTextWatcher(EditText field)
        {
            this.field = field;
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
         * Sets an error if the watched field is empty.
         * @param charSequence the new text of the field, represented as a
         * CharSequence object.
         * @param i the "start" of the text being being changed
         * @param i2 the length of the old text that has been replaced.
         * @param i3 the number of characters that have been replaced.
         */
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {
            if (charSequence.toString().length() == 0)
                field.setError("This must be completed");

        }
        
        /**
         * Method not supported
         * @param editable 
         */
        @Override
        public void afterTextChanged(Editable editable)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

}
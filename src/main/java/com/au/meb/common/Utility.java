package com.au.meb.common;

import com.vaadin.data.HasValue;
import com.vaadin.ui.TextField;

/**
 * Created by ayhanugurlu on 11/5/18.
 */
public class Utility {

    public static HasValue.ValueChangeListener<String> getValueChangeListener(){

        HasValue.ValueChangeListener<String> valueChangeListener = new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> event) {
                try {
                    if(event.getValue().length() > 0 ){
                        new Integer(event.getValue());
                    }

                } catch (Exception e) {
                    ((TextField) event.getComponent()).setValue("");
                }


            }
        };
        return  valueChangeListener;
    }
}

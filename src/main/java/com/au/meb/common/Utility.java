package com.au.meb.common;

import com.vaadin.data.HasValue;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

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

    public static Image getImage(String path){
        Image image = null;

        try {
            File file = new File(path);

            // Image as a file resource
            FileResource resource = new FileResource(file);
            // Show the image in the application
            image = new Image("", resource);
            image.setWidth("50%");
            image.setHeight("100%");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }


    public static Resource getResource(String path){

        File file = new File(path);

        FileResource resource = new FileResource(file);

        return  resource;
    }
}

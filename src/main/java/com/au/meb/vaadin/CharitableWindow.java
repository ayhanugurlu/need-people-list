package com.au.meb.vaadin;

import com.vaadin.ui.*;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
public class CharitableWindow extends Window {

    public CharitableWindow(){
        this.setClosable(false);
        FormLayout formLayout = new FormLayout();
        TextField name = new TextField("isim");
        TextField surname = new TextField("soyisim");
        TextField gsm = new TextField("gsm");
        TextField mail = new TextField("mail");
        CheckBox hasStudent = new CheckBox("Ogrencisi var mi");
        Button saveButton = new Button("Kaydet");
        Button closeButton = new Button("Vazgec");
        formLayout.addComponent(name);
        formLayout.addComponent(surname);
        formLayout.addComponent(gsm);
        formLayout.addComponent(mail);
        formLayout.addComponent(hasStudent);

        formLayout.addComponent(saveButton);
        formLayout.addComponent(closeButton);
        saveButton.addClickListener(event ->{

        });
        closeButton.addClickListener(event ->{
            UI.getCurrent().removeWindow(this);
        });

    }

}

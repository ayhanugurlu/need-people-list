package com.au.meb.vaadin;

import com.au.meb.common.RecordState;
import com.au.meb.dto.CharitableDTO;
import com.au.meb.service.NeedPeopleService;
import com.vaadin.ui.*;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
public class CharitableWindow extends Window {


    private NeedPeopleService needPeopleService;

    private long needPeopleRecordId;

    public CharitableWindow(NeedPeopleService needPeopleService,long needPeopleRecordId) {
        this.needPeopleRecordId = needPeopleRecordId;
        this.needPeopleService = needPeopleService;
        this.setClosable(false);
        this.setModal(false);
        this.setResizable(false);
        this.setCaption("Hayirserver Bilgileri");
        FormLayout formLayout = new FormLayout();
        TextField name = new TextField("isim");
        TextField surname = new TextField("soyisim");
        TextField tck = new TextField("tck");
        TextField gsm = new TextField("gsm");
        TextField mail = new TextField("mail");
        CheckBox hasStudent = new CheckBox("Ogrencisi var mi");
        Button saveButton = new Button("Kaydet");
        Button closeButton = new Button("Vazgec");
        formLayout.addComponent(name);
        formLayout.addComponent(surname);
        formLayout.addComponent(tck);
        formLayout.addComponent(gsm);
        formLayout.addComponent(mail);
        formLayout.addComponent(hasStudent);
        formLayout.addComponent(saveButton);
        formLayout.addComponent(closeButton);

        saveButton.addClickListener(event -> {
            CharitableDTO charitableDTO = CharitableDTO.builder().gsm(gsm.getValue()).
                    hasStudent(hasStudent.getValue()).mail(mail.getValue()).name(name.getValue()).surname(surname.getValue()).build();
            needPeopleService.updateState(needPeopleRecordId,RecordState.RESERVED,charitableDTO);
            Notification.show("Kayit Rezerve Edildi", Notification.Type.HUMANIZED_MESSAGE);

        });
        closeButton.addClickListener(event -> {
            UI.getCurrent().removeWindow(this);
        });
        formLayout.setSizeUndefined();
        this.setContent(formLayout);
        this.center();
    }

}

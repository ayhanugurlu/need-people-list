package com.au.meb.vaadin;

import com.au.meb.common.Gender;
import com.au.meb.common.RecordState;
import com.au.meb.common.Size;
import com.au.meb.dto.CharitableDTO;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.service.NeedPeopleService;
import com.vaadin.ui.*;

import java.util.Arrays;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
public class NeedPeopleWindow extends Window {


    private NeedPeopleService needPeopleService;

    private long needPeopleRecordId;

    public NeedPeopleWindow(NeedPeopleService needPeopleService, long needPeopleRecordId) {
        NeedPeopleDTO needPeopleDTO = needPeopleService.getNeedPeopleDTO(needPeopleRecordId);
        FormLayout formLayout = new FormLayout();
        formLayout.setWidthUndefined();
        TextField needPersonNameText = new TextField("Ihtiyac Sahibi Adi");
        TextField needPersonSurnameText = new TextField("Ihtiyac Sahibi Soyadi");
        TextArea needPersonAddress = new TextArea("Adress");
        TextArea needListText = new TextArea("Ihtiyac Listesi");
        TextField schoolName = new TextField("Okul Adi");
        RadioButtonGroup<Gender> genderRadioButtonGroup = new RadioButtonGroup<>("Cinsiyet", Arrays.asList(Gender.values()));
        TextField age = new TextField("Yas");
        RadioButtonGroup<Size> sizeRadioButtonGroup = new RadioButtonGroup<>("Size", Arrays.asList(Size.values()));
        TextField footSize = new TextField("Ayak no");

        Button saveButton = new Button("Guncelle");
        Button closeButton = new Button("Vazgec");
        HorizontalLayout horizontalLayout =new HorizontalLayout();
        horizontalLayout.addComponents(saveButton,closeButton);
        formLayout.addComponents(needPersonNameText, needPersonSurnameText, needPersonAddress,
                schoolName,genderRadioButtonGroup,age,sizeRadioButtonGroup,footSize, needListText, horizontalLayout);


        needPersonNameText.setValue(needPeopleDTO.getName());
        needPersonSurnameText.setValue(needPeopleDTO.getSurname());
        needListText.setValue(needPeopleDTO.getNeeds());
        footSize.setValue(""+needPeopleDTO.getFootSize());
        age.setValue(""+needPeopleDTO.getAge());
        schoolName.setValue(needPeopleDTO.getSchoolName());
        genderRadioButtonGroup.setValue(needPeopleDTO.getGender());
        sizeRadioButtonGroup.setValue(needPeopleDTO.getSize());
        needPersonAddress.setValue(needPeopleDTO.getAddress());


        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (needPersonNameText.getValue() != null && needPersonNameText.getValue().trim().length() > 0
                        && needPersonSurnameText.getValue() != null && needPersonSurnameText.getValue().trim().length() > 0
                        && needPersonAddress.getValue() != null && needPersonAddress.getValue().trim().length() > 0
                        && needListText.getValue() != null && needListText.getValue().trim().length() > 0
                        && footSize.getValue() != null && footSize.getValue().length() > 0
                        && age.getValue() != null && age.getValue().length() > 0
                        && schoolName.getValue() != null && schoolName.getValue().length() > 0
                        && genderRadioButtonGroup.getSelectedItem().isPresent() && sizeRadioButtonGroup.getSelectedItem().isPresent()
                        ) {
                    NeedPeopleDTO needPeopleDTO = NeedPeopleDTO.builder().name(needPersonNameText.getValue()).surname(needPersonSurnameText.getValue()).schoolName(schoolName.getValue()).
                            gender(genderRadioButtonGroup.getSelectedItem().get()).address(needPersonAddress.getValue()).
                            size(sizeRadioButtonGroup.getSelectedItem().get()).age(new Integer(age.getValue())).gender(genderRadioButtonGroup.getSelectedItem().get()).footSize(new Integer(footSize.getValue()))
                            .needs(needListText.getValue()).state(RecordState.ACTIVE).build();
                    needPeopleDTO.setId(needPeopleRecordId);
                    needPeopleService.save(needPeopleDTO);
                    needPersonNameText.clear();
                    needPersonSurnameText.clear();
                    needListText.clear();
                    footSize.clear();
                    age.clear();
                    schoolName.clear();
                    genderRadioButtonGroup.clear();
                    sizeRadioButtonGroup.clear();
                    needPersonAddress.clear();
                    Notification.show("Kayit Eklendi", Notification.Type.HUMANIZED_MESSAGE);
                    UI.getCurrent().removeWindow(NeedPeopleWindow.this);
                } else {
                    Notification.show("Tum alanlar dolu olmali", Notification.Type.HUMANIZED_MESSAGE);
                }

            }
        });

        closeButton.addClickListener(event -> {
            UI.getCurrent().removeWindow(this);
        });
        formLayout.setSizeUndefined();
        this.setContent(formLayout);
        this.center();
    }

}

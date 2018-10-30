package com.au.meb.vaadin.admin;

import com.au.meb.common.Gender;
import com.au.meb.common.RecordState;
import com.au.meb.common.Size;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.service.NeedPeopleService;
import com.au.meb.vaadin.NeedPeopleAdminUI;
import com.au.meb.vaadin.NeedPeopleListView;
import com.au.meb.vaadin.NeedPeopleUI;
import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@SpringView(name = NeedPeopleSaveView.NAME)
public class NeedPeopleSaveView extends VerticalLayout implements View {
    public static final String NAME = "NeedPeopleSaveView";


    @Autowired
    NeedPeopleService needPeopleService;

    TextField needPersonNameText = new TextField("Ihtiyac Sahibi Adi");
    TextField needPersonSurnameText = new TextField("Ihtiyac Sahibi Soyadi");
    TextArea needPersonAddress = new TextArea("Adress");
    TextField needListText = new TextField("Ihtiyac Listesi");
    TextField schoolName = new TextField("Okul Adi");
    RadioButtonGroup<Gender> genderRadioButtonGroup = new RadioButtonGroup<>("Cinsiyet", Arrays.asList(Gender.values()));
    TextField age = new TextField("Yas");
    RadioButtonGroup<Size> sizeRadioButtonGroup = new RadioButtonGroup<>("Size", Arrays.asList(Size.values()));
    TextField footSize = new TextField("Ayak no");
    private Button saveButton = new Button("Save");

    private Button listPageButton = new Button("ListPage");

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {

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
        age.addValueChangeListener(valueChangeListener);
        footSize.addValueChangeListener(valueChangeListener);

        FormLayout formLayout = new FormLayout();
        this.setSizeUndefined();
        formLayout.setWidthUndefined();
        formLayout.addComponents(listPageButton, needPersonNameText, needPersonSurnameText, needPersonAddress,schoolName,genderRadioButtonGroup,age,sizeRadioButtonGroup,footSize, needListText, saveButton);
        this.addComponent(formLayout);
        this.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

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
                } else {
                    Notification.show("Tum alanlar dolu olmali", Notification.Type.HUMANIZED_MESSAGE);
                }

            }
        });

        listPageButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleAdminUI) UI.getCurrent()).router(NeedPeopleListView.NAME);
            }
        });
    }
}

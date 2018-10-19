package com.au.meb.vaadin.admin;

import com.au.meb.common.Gender;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.service.NeedPeopleService;
import com.au.meb.vaadin.NeedPeopleListView;
import com.au.meb.vaadin.NeedPeopleUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@SpringView(name = NeedPeopleSchoolSaveView.NAME)
public class NeedPeopleSchoolSaveView extends VerticalLayout implements View {
    public static final String NAME = "NeedPeopleSaveView";


    @Autowired
    NeedPeopleService needPeopleService;


    TextField needPersonNameText = new TextField("Ihtiyac Sahibi Adi");
    TextField needPersonSurnameText = new TextField("Ihtiyac Sahibi Soyadi");
    TextArea needPersonAddress = new TextArea("Adress");
    TextArea needListText = new TextArea("Ihtiyac Listesi");

    private Button saveButton = new Button("Save");
    private Button listPageButton = new Button("ListPage");

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {

        FormLayout formLayout = new FormLayout();
        this.setSizeFull();
        formLayout.setWidthUndefined();
        formLayout.addComponents(listPageButton, needPersonNameText, needPersonSurnameText, needPersonAddress, needListText, saveButton);
        this.addComponent(formLayout);
        this.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (needPersonNameText.getValue() != null && needPersonNameText.getValue().trim().length() > 0
                        && needPersonSurnameText.getValue() != null && needPersonSurnameText.getValue().trim().length() > 0
                        && needPersonAddress.getValue() != null && needPersonAddress.getValue().trim().length() > 0
                        && needListText.getValue() != null && needListText.getValue().trim().length() > 0
                        ) {
                    NeedPeopleDTO needPeopleDTO = NeedPeopleDTO.builder().name(needPersonNameText.getValue()).surname(needPersonSurnameText.getValue()).address(needPersonAddress.getValue())
                            .needs(needListText.getValue()).build();
                    needPeopleService.save(needPeopleDTO);
                    needPersonNameText.setValue("");
                    needPersonSurnameText.setValue("");
                    needListText.setValue("");
                    needPersonAddress.setValue("");
                    Notification.show("Kayit Eklendi", Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    Notification.show("Tum alanlar dolu olmali", Notification.Type.HUMANIZED_MESSAGE);
                }

            }
        });

        listPageButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleUI) UI.getCurrent()).router(NeedPeopleListView.NAME);
            }
        });
    }
}

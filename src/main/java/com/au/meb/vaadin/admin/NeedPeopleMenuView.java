package com.au.meb.vaadin.admin;

import com.au.meb.vaadin.NeedPeopleAdminUI;
import com.au.meb.vaadin.NeedPeopleListView;
import com.au.meb.vaadin.NeedPeopleUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

/**
 * Created by Ayhan.Ugurlu on 19/10/2018
 */
@SpringView(name = NeedPeopleMenuView.NAME)
public class NeedPeopleMenuView extends VerticalLayout implements View {
    public static final String NAME = "NeedPeopleMenuView";



    private Button newRecordButton = new Button("Yeni Kayit");
    private Button allList = new Button("Tum Liste");
    private Button completedRecordList = new Button("Tamamlanan");
    private Button schoolNewRecord = new Button("Okul Yeni Kayit");
    private Button charitableList = new Button("Hayirsever");


    private Button listPageButton = new Button("ListPage");

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {

        FormLayout formLayout = new FormLayout();
        this.setSizeFull();
        formLayout.setWidthUndefined();
        formLayout.addComponents(newRecordButton, allList, completedRecordList, schoolNewRecord, charitableList);
        this.addComponent(formLayout);
        this.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        listPageButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleUI) UI.getCurrent()).router(NeedPeopleListView.NAME);
            }
        });
        newRecordButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleAdminUI) UI.getCurrent()).router(NeedPeopleSaveView.NAME);
            }
        });
    }


}

package com.au.meb.vaadin.admin;


import com.au.meb.common.listener.Query;
import com.au.meb.dto.UserDTO;
import com.au.meb.vaadin.NeedPeopleAdminUI;
import com.au.meb.vaadin.NeedPeopleListView;
import com.au.meb.vaadin.NeedPeopleUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
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
    private Button logout = new Button("Cikis");

    private Button listPageButton = new Button("ListPage");

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {

        FormLayout formLayout = new FormLayout();
        this.setSizeFull();
        formLayout.setWidthUndefined();
        formLayout.addComponents(newRecordButton, allList, completedRecordList, schoolNewRecord, charitableList,logout);
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

        schoolNewRecord.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleAdminUI) UI.getCurrent()).router(NeedPeopleSchoolSaveView.NAME);
            }
        });

        allList.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                VaadinSession.getCurrent().getSession().setAttribute(Query.class.getName(),Query.ALL);
                ((NeedPeopleAdminUI) UI.getCurrent()).router(NeedPeopleListView.NAME);
            }
        });

        completedRecordList.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                VaadinSession.getCurrent().getSession().setAttribute(Query.class.getName(),Query.COMPLETED);
                ((NeedPeopleAdminUI) UI.getCurrent()).router(NeedPeopleListView.NAME);
            }
        });
        logout.addClickListener(event ->{
            VaadinSession.getCurrent().setAttribute(UserDTO.class,null);
            ((NeedPeopleAdminUI)UI.getCurrent()).router(LoginView.NAME);
        });
    }


}

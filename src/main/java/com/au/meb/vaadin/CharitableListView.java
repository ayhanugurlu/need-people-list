package com.au.meb.vaadin;

import com.au.meb.common.AuthrityType;
import com.au.meb.common.RecordState;
import com.au.meb.common.listener.Query;
import com.au.meb.dto.CharitableDTO;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.dto.UserDTO;
import com.au.meb.service.NeedPeopleService;
import com.au.meb.vaadin.admin.LoginView;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@SpringView(name = CharitableListView.NAME)
public class CharitableListView extends VerticalLayout implements View {

    public static final String NAME = "CharitableListView";

    @Autowired
    NeedPeopleService needPeopleService;

    private Grid<CharitableDTO> charitableListGrid = new Grid();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {




        CharitableListView.this.setSizeFull();

        if (UI.getCurrent() instanceof NeedPeopleAdminUI) {
            Button managerViewButton = new Button("Yonetici Girisi");
            managerViewButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    ((NeedPeopleAdminUI) UI.getCurrent()).router(LoginView.NAME);
                }
            });
            CharitableListView.this.addComponent(managerViewButton);
        }
        this.setHeightUndefined();
        this.setSpacing(false);
        charitableListGrid.setSizeFull();

        charitableListGrid.addColumn(CharitableDTO::getName).setCaption("Name");
        charitableListGrid.addColumn(CharitableDTO::getSurname).setCaption("Surname");
        charitableListGrid.addColumn(CharitableDTO::getTck).setCaption("Tck");
        charitableListGrid.addColumn(CharitableDTO::getMail).setCaption("Mail");
        charitableListGrid.addColumn(CharitableDTO::getGsm).setCaption("Gsm");

        CharitableListView.this.addComponent(charitableListGrid);

        charitableListGrid.setData(needPeopleService.getAllCharitableDTO());







    }


}

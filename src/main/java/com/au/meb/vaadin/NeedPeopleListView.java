package com.au.meb.vaadin;

import com.au.meb.common.AuthrityType;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.dto.UserDTO;
import com.au.meb.service.NeedPeopleService;
import com.au.meb.vaadin.admin.LoginView;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@SpringView(name = NeedPeopleListView.NAME)
public class NeedPeopleListView extends VerticalLayout implements View {

    public static final String NAME = "NeedPeopleListView";

    @Autowired
    NeedPeopleService needPeopleService;

    private Grid<NeedPeopleDTO> needListGrid = new Grid();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {

        FormLayout formLayout = new FormLayout();
        this.setSizeFull();
        this.addComponent(formLayout);
        formLayout.setWidthUndefined();

        Button loginButton = new Button("Yonetici Girisi");
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleUI)UI.getCurrent()).router(LoginView.NAME);
            }
        });
        formLayout.addComponent(loginButton);
        formLayout.addComponent(needListGrid);
        this.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        needListGrid.setItems(needPeopleService.list());
        needListGrid.addColumn(NeedPeopleDTO::getId).setCaption("Id");


        UserDTO userDTO = UI.getCurrent().getSession().getAttribute(UserDTO.class);

        needListGrid.addColumn(NeedPeopleDTO::getAddress).setCaption("Adress");
        needListGrid.addColumn(NeedPeopleDTO::getNeeds).setCaption("Ihtiyac Listesi");

        if(userDTO != null && userDTO.getAuthority() == AuthrityType.ADMIN){
            needListGrid.addColumn(NeedPeopleDTO::getName).setCaption("Isim");

            needListGrid.addColumn(NeedPeopleDTO::getSurname).setCaption("Soyisim");

            needListGrid.addComponentColumn(needPeopleDTO -> {
                Button complete = new Button("Tamamla");
                complete.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        needPeopleService.complete(needPeopleDTO.getId());
                        ((ListDataProvider)needListGrid.getDataProvider()).getItems().remove(needPeopleDTO);
                        needListGrid.getDataProvider().refreshAll();
                        Notification.show("Kayit Tamamlandi",Notification.Type.HUMANIZED_MESSAGE);
                    }
                });
                return complete;
            });

        }

    }

}

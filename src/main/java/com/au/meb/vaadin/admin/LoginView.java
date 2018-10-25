package com.au.meb.vaadin.admin;

import com.au.meb.common.AuthrityType;
import com.au.meb.common.exception.AuthenticationException;
import com.au.meb.db.NeedPeople;
import com.au.meb.dto.UserDTO;
import com.au.meb.service.UserService;
import com.au.meb.vaadin.NeedPeopleAdminUI;
import com.au.meb.vaadin.NeedPeopleListView;
import com.au.meb.vaadin.NeedPeopleUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@SpringView(name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View {
    public static final String NAME = "LoginView";

    @Autowired
    UserService userService;


    private TextField userNameInput = new TextField("Kullanici Adi");
    private PasswordField passwordInput = new PasswordField("Sifre");
    private Button loginButton = new Button("Giris");

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        UserDTO userDTO = VaadinSession.getCurrent().getAttribute(UserDTO.class);
        if(userDTO == null){
            buildPage();
        }else{
            ((NeedPeopleAdminUI)UI.getCurrent()).router(NeedPeopleMenuView.NAME);
        }

    }


    public void buildPage() {
        FormLayout formLayout = new FormLayout();
        this.setSizeFull();
        formLayout.setWidthUndefined();
        this.addComponent(formLayout);
        formLayout.addComponents(userNameInput, passwordInput, loginButton);
        this.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        loginButton.addClickListener(action -> {
            String username = userNameInput.getValue();
            String password = passwordInput.getValue();
            try {
                UserDTO userDTO = userService.login(username, password);
                UI.getCurrent().getSession().setAttribute(UserDTO.class, userDTO);
                ((NeedPeopleAdminUI) UI.getCurrent()).router(NeedPeopleMenuView.NAME);
            } catch (AuthenticationException e) {
                Notification.show("Gecersiz kullanici adi password", Notification.Type.ERROR_MESSAGE);
            }
            ;
        });
    }

}

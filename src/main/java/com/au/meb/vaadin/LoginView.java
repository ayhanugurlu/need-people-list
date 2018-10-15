package com.au.meb.vaadin;

import com.au.meb.common.AuthrityType;
import com.au.meb.common.exception.AuthenticationException;
import com.au.meb.dto.UserDTO;
import com.au.meb.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
public class LoginView extends VerticalLayout implements View {
    public static final String NAME = "LoginView";

    @Autowired
    UserService userService;

    private Label userNameLabel = new Label("Email");
    private TextField userNameInput = new TextField();
    private Label passwordLabel = new Label("Email");
    private Label passwordInput = new Label("Email");
    private Button loginButton = new Button();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }


    public void buildPage(){
        HorizontalLayout userNameLayout = new HorizontalLayout();
        userNameLayout.addComponentsAndExpand(userNameLabel,userNameInput);

        HorizontalLayout passwordLayout = new HorizontalLayout();
        passwordLayout.addComponentsAndExpand(passwordLabel,passwordInput);

        this.addComponentsAndExpand(userNameLayout,passwordLayout);
        this.addComponent(loginButton);
        this.setComponentAlignment(userNameLayout, Alignment.BOTTOM_CENTER);
        this.setComponentAlignment(passwordLayout, Alignment.BOTTOM_CENTER);
        this.setComponentAlignment(loginButton, Alignment.BOTTOM_CENTER);

        loginButton.addClickListener(action ->{
            String username = userNameInput.getValue();
            String password = passwordInput.getValue();
            try {
                UserDTO userDTO =  userService.login(username,password);
                if(userDTO.getAuthority() == AuthrityType.ADMIN){
                    ((NeedPeopleUI)UI.getCurrent()).router(NeedPeopleSaveView.NAME);
                }else{
                    ((NeedPeopleUI)UI.getCurrent()).router(NeedPeopleListView.NAME);
                }

            } catch (AuthenticationException e) {
                Notification.show("Gecersiz kullanici adi password", Notification.Type.ERROR_MESSAGE);
            }
            ;
        });
    }

}

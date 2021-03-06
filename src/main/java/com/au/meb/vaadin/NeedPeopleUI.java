package com.au.meb.vaadin;


import com.au.meb.dto.UserDTO;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */

@SpringUI(path = "application")
public class NeedPeopleUI extends UI {


    @Autowired
    SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(springViewProvider);
        navigator.setErrorView(ErrorView.class);
        navigator.addView(NeedPeopleListView.NAME, NeedPeopleListView.class);
        navigator.addView(ErrorView.NAME, ErrorView.class);
        router(NeedPeopleListView.NAME);

    }



    public void router(String route) {
        getNavigator().navigateTo(route);
    }
}

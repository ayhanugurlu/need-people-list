package com.au.meb.vaadin;


import com.au.meb.dto.UserDTO;
import com.au.meb.vaadin.admin.LoginView;
import com.au.meb.vaadin.admin.NeedPeopleMenuView;
import com.au.meb.vaadin.admin.NeedPeopleSaveView;
import com.au.meb.vaadin.admin.NeedPeopleSchoolSaveView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */

@SpringUI(path = "application/admin")
public class NeedPeopleAdminUI extends UI {


    @Autowired
    SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {


        Navigator navigator = new Navigator(this, this);

        springViewProvider.setAccessDeniedViewClass(LoginView.class);
        navigator.addProvider(springViewProvider);
        navigator.setErrorView(ErrorView.class);

        navigator.addView(LoginView.NAME, LoginView.class);
        navigator.addView(NeedPeopleListView.NAME, NeedPeopleListView.class);
        navigator.addView(NeedPeopleSaveView.NAME, NeedPeopleSaveView.class);
        navigator.addView(NeedPeopleMenuView.NAME, NeedPeopleMenuView.class);
        navigator.addView(NeedPeopleSchoolSaveView.NAME, NeedPeopleSchoolSaveView.class);
        navigator.addView(ErrorView.NAME, ErrorView.class);


        UserDTO userDTO = UI.getCurrent().getSession().getAttribute(UserDTO.class);
        if(userDTO != null){
            router(NeedPeopleMenuView.NAME);
        }else{
            router(LoginView.NAME);
        }


    }



    public void router(String route) {
        getNavigator().navigateTo(route);
    }
}

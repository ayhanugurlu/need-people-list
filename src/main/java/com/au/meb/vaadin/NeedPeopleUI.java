package com.au.meb.vaadin;


import com.au.meb.common.AuthrityType;
import com.au.meb.common.Constant;
import com.au.meb.dto.UserDTO;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */

@SpringUI(path = "assessment")
public class NeedPeopleUI extends UI {


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
        navigator.addView(ErrorView.NAME, ErrorView.class);


        if(UI.getCurrent().getSession().getAttribute(Constant.USER_INFO)!= null){
            UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Constant.USER_INFO);
            if(userDTO.getAuthority() == AuthrityType.ADMIN){
                router(NeedPeopleSaveView.NAME);
            }else{
                router(NeedPeopleListView.NAME);
            }


        }else{
            router(LoginView.NAME);
        }

    }



    public void router(String route) {
        getNavigator().navigateTo(route);
    }
}

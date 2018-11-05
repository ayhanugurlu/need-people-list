package com.au.meb.vaadin;

import com.au.meb.common.AuthrityType;
import com.au.meb.common.RecordState;
import com.au.meb.common.listener.Query;
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


        NeedPeopleListView.this.setSizeFull();

        if (UI.getCurrent() instanceof NeedPeopleAdminUI) {
            Button managerViewButton = new Button("Yonetici Girisi");
            managerViewButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    ((NeedPeopleAdminUI) UI.getCurrent()).router(LoginView.NAME);
                }
            });
            NeedPeopleListView.this.addComponent(managerViewButton);
        }
        this.addComponent(needListGrid);
        this.setHeightUndefined();
        this.setSpacing(false);

        needListGrid.setSizeFull();


        needListGrid.setItems(loadData());
        needListGrid.addColumn(NeedPeopleDTO::getId).setCaption("Id");


        UserDTO userDTO = UI.getCurrent().getSession().getAttribute(UserDTO.class);

        needListGrid.addColumn(NeedPeopleDTO::getAddress).setCaption("Adress");
        needListGrid.addColumn(NeedPeopleDTO::getNeeds).setCaption("Ihtiyac Listesi");

        final String buttonCaption;
        if (userDTO != null && userDTO.getAuthority() == AuthrityType.ADMIN) {
            needListGrid.addColumn(NeedPeopleDTO::getName).setCaption("Isim");
            needListGrid.addColumn(NeedPeopleDTO::getSurname).setCaption("Soyisim");
            buttonCaption = "Tamamla";
        } else {
            buttonCaption = "Rezerve Et";
        }
        needListGrid.addComponentColumn(needPeopleDTO -> {
            Button actionButton = new Button(buttonCaption);
            actionButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    CharitableWindow charitableWindow = null;
                    if (userDTO != null && userDTO.getAuthority() == AuthrityType.ADMIN) {

                        charitableWindow = new CharitableWindow(needPeopleService, needPeopleDTO.getId(), RecordState.COMPLETED);
                        UI.getCurrent().addWindow(charitableWindow);

                        //needPeopleService.updateState(needPeopleDTO.getId(), RecordState.COMPLETED,null);


                    } else {
                        charitableWindow = new CharitableWindow(needPeopleService, needPeopleDTO.getId(), RecordState.RESERVED);
                        UI.getCurrent().addWindow(charitableWindow);
                        //needPeopleService.updateState(needPeopleDTO.getId(), RecordState.RESERVED);

                    }
                    charitableWindow.addCloseListener(closeEvent -> {
                        ((ListDataProvider) needListGrid.getDataProvider()).getItems().remove(needPeopleDTO);
                        needListGrid.setItems(loadData());
                        needListGrid.getDataProvider().refreshAll();
                    });
                }
            });
            return actionButton;
        });


    }

    private List<NeedPeopleDTO>  loadData(){
        List<NeedPeopleDTO> needPeopleList = new ArrayList<>();
        if (VaadinSession.getCurrent().getSession().getAttribute(Query.class.getName()) == Query.ALL) {
            Arrays.stream(RecordState.values()).forEach(state -> {
                needPeopleList.addAll(needPeopleService.list(state));
            });
        } else if (VaadinSession.getCurrent().getSession().getAttribute(Query.class.getName()) == Query.COMPLETED) {
            needPeopleList.addAll(needPeopleService.list(RecordState.COMPLETED));
        } else {
            needPeopleList.addAll(needPeopleService.list(RecordState.ACTIVE));
        }
        return needPeopleList;
    }


}

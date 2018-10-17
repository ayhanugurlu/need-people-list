package com.au.meb.vaadin;

import com.au.meb.common.AuthrityType;
import com.au.meb.common.Constant;
import com.au.meb.db.NeedPeople;
import com.au.meb.db.NeedPeopleRepository;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.dto.UserDTO;
import com.au.meb.service.NeedPeopleService;
import com.au.meb.service.NeedPeopleServiceImpl;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Stream;

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
        this.addComponent(needListGrid);
        needListGrid.setItems(needPeopleService.list());
        needListGrid.addColumn(NeedPeopleDTO::getId);


        UserDTO userDTO = UI.getCurrent().getSession().getAttribute(UserDTO.class);
        if(userDTO != null && userDTO.getAuthority() == AuthrityType.ADMIN){
            needListGrid.addColumn(NeedPeopleDTO::getName);
            needListGrid.addColumn(NeedPeopleDTO::getSurname);
            


        }
        needListGrid.addColumn(NeedPeopleDTO::getNeeds);
    }

}

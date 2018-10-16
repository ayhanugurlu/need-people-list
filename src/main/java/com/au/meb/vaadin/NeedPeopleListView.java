package com.au.meb.vaadin;

import com.au.meb.db.NeedPeople;
import com.au.meb.db.NeedPeopleRepository;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.service.NeedPeopleService;
import com.au.meb.service.NeedPeopleServiceImpl;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
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
        needListGrid.setData(needPeopleService.list());
    }

}

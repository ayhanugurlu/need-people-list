package com.au.meb.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
public class NeedPeopleListView extends VerticalLayout implements View {

    public static final String NAME = "NeedPeopleListView";

    private Grid needListGrid = new Grid();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {
        this.addComponent(needListGrid);
    }

}

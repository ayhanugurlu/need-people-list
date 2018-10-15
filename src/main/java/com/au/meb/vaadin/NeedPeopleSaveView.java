package com.au.meb.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
public class NeedPeopleSaveView extends VerticalLayout implements View {
    public static final String NAME = "NeedPeopleSaveView";

    Label needPersonLabel = new Label("Ihtiyac Sahibi Adi");
    TextField needPersonText = new TextField();
    Label needListLabel = new Label("Ihtiyac Sahibi Adi");
    TextField needListText = new TextField();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {
        HorizontalLayout personLayout = new HorizontalLayout();
        personLayout.addComponentsAndExpand(needListLabel,needPersonText);
        HorizontalLayout listLayout = new HorizontalLayout();
        listLayout.addComponentsAndExpand(needListLabel,needListText);

        this.addComponent(personLayout);
        this.addComponent(listLayout);
    }
}

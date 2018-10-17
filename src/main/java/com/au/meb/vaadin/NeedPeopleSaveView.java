package com.au.meb.vaadin;

import com.au.meb.db.NeedPeople;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.service.NeedPeopleService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@SpringView(name = NeedPeopleSaveView.NAME)
public class NeedPeopleSaveView extends VerticalLayout implements View {
    public static final String NAME = "NeedPeopleSaveView";


    @Autowired
    NeedPeopleService needPeopleService;

    Label needPersonNameLabel = new Label("Ihtiyac Sahibi Adi");
    TextField needPersonNameText = new TextField();
    Label needPersonSurnameLabel = new Label("Ihtiyac Sahibi Soyadi");
    TextField needPersonSurnameText = new TextField();
    Label needListLabel = new Label("Ihtiyac Listesi");
    TextArea needListText = new TextArea();
    private Button loginButton = new Button("Save");

    private Button listPageButton = new Button("ListPage");

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buildPage();

    }

    private void buildPage() {
        HorizontalLayout personNameLayout = new HorizontalLayout();
        personNameLayout.addComponents(needPersonNameLabel,needPersonNameText);
        HorizontalLayout personSurnameLayout = new HorizontalLayout();
        personNameLayout.addComponents(needPersonSurnameLabel,needPersonSurnameText);
        HorizontalLayout listLayout = new HorizontalLayout();
        listLayout.addComponents(needListLabel,needListText);
        this.addComponent(listPageButton);
        this.addComponent(personNameLayout);
        this.addComponent(personSurnameLayout);
        this.addComponent(listLayout);
        this.addComponent(loginButton);

        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                NeedPeopleDTO needPeopleDTO =  NeedPeopleDTO.builder().name(needPersonNameText.getValue()).surname(needPersonSurnameText.getValue()).needs(needListText.getValue()).build();
                needPeopleService.save(needPeopleDTO);
                needPersonNameText.setValue("");
                needPersonSurnameText.setValue("");
                needListText.setValue("");
                Notification.show("Kayit Eklendi", Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        listPageButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((NeedPeopleUI)UI.getCurrent()).router(NeedPeopleListView.NAME);
            }
        });
    }
}

package com.au.meb.service.mapper;

import com.au.meb.db.Charitable;
import com.au.meb.db.NeedPeople;
import com.au.meb.db.NeedSchool;
import com.au.meb.db.User;
import com.au.meb.dto.CharitableDTO;
import com.au.meb.dto.NeedPeopleDTO;
import com.au.meb.dto.UserDTO;
import com.au.meb.vaadin.admin.NeedPeopleSchoolSaveView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by Ayhan.Ugurlu on 16/10/2018
 */
@Component
public class ServiceMapper extends ConfigurableMapper {


    protected void configure(MapperFactory factory) {
        factory.classMap(NeedPeopleDTO.class, NeedPeople.class)
                .byDefault()
                .register();
        factory.classMap(NeedPeople.class, NeedPeopleDTO.class)
                .byDefault()
                .register();

        factory.classMap(NeedPeopleDTO.class, NeedSchool.class)
                .byDefault()
                .register();
        factory.classMap(NeedSchool.class, NeedPeopleDTO.class)
                .byDefault()
                .register();

        factory.classMap(UserDTO.class, User.class)
                .byDefault()
                .register();


        factory.classMap(User.class, UserDTO.class)
                .byDefault()
                .register();

        factory.classMap(Charitable.class, CharitableDTO.class)
                .byDefault()
                .register();

        factory.classMap(CharitableDTO.class, Charitable.class)
                .byDefault()
                .register();
    }

}

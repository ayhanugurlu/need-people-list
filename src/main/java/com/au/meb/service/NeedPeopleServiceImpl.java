package com.au.meb.service;

import com.au.meb.db.NeedPeople;
import com.au.meb.db.NeedPeopleRepository;
import com.au.meb.dto.NeedPeopleDTO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ayhan.Ugurlu on 16/10/2018
 */

@Service
public class NeedPeopleServiceImpl implements NeedPeopleService {


    @Qualifier("serviceMapper")
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    NeedPeopleRepository needPeopleRepository;

    @Override
    public void save(NeedPeopleDTO needPeopleDTO) {

        NeedPeople needPeople = mapperFacade.map(needPeopleDTO, NeedPeople.class);
        needPeopleRepository.save(needPeople);
    }

    @Override
    public List<NeedPeopleDTO> list() {

        List<NeedPeople>  needPeoples = needPeopleRepository.findAll();
        List<NeedPeopleDTO> needPeopleDTOS =   needPeoples.stream().map(needPeople -> {
            return mapperFacade.map(needPeople,NeedPeopleDTO.class);
        }).collect(Collectors.toList());
        return needPeopleDTOS;
    }
}

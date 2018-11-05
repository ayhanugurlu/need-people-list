package com.au.meb.service;

import com.au.meb.common.RecordState;
import com.au.meb.db.Charitable;
import com.au.meb.db.CharitableRepository;
import com.au.meb.db.NeedPeople;
import com.au.meb.db.NeedPeopleRepository;
import com.au.meb.dto.CharitableDTO;
import com.au.meb.dto.NeedPeopleDTO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    CharitableRepository charitableRepository;

    @Override
    public void save(NeedPeopleDTO needPeopleDTO) {

        NeedPeople needPeople = mapperFacade.map(needPeopleDTO, NeedPeople.class);
        needPeopleRepository.save(needPeople);
    }

    @Override
    public List<NeedPeopleDTO> list(RecordState query) {

        List<NeedPeople>  needPeoples = needPeopleRepository.findByState(query);
        List<NeedPeopleDTO> needPeopleDTOS =   needPeoples.stream().map(needPeople -> {
            return mapperFacade.map(needPeople,NeedPeopleDTO.class);
        }).collect(Collectors.toList());
        return needPeopleDTOS;
    }

    @Override
    public void updateState(long id, RecordState recordState, CharitableDTO charitableDTO) {

        Optional<NeedPeople> needPeople = needPeopleRepository.findById(id);

        if(charitableDTO == null){
            needPeople.ifPresent(needPeople1 -> {
                Optional<Charitable> charitableOptional =  charitableRepository.findByTck(charitableDTO.getTck());
                Charitable charitable = null;
                if(!charitableOptional.isPresent()){
                    charitable = charitableOptional.get();
                }else{
                    charitable = mapperFacade.map(charitableDTO,Charitable.class);
                    charitableRepository.save(charitable);
                }
                charitable.getNeedPeople().add(needPeople1);

                needPeople1.setState(recordState);
                needPeopleRepository.save(needPeople1);
            });
        }

    }

    @Override
    public NeedPeopleDTO getNeedPeopleDTO(long id) {
        NeedPeopleDTO needPeopleDTO = null;
        Optional<NeedPeople> needPeople =  needPeopleRepository.findById(id);
        if(needPeople.isPresent()){
            needPeopleDTO =  mapperFacade.map(needPeople.get(),NeedPeopleDTO.class);
        }
        return needPeopleDTO;
    }

    @Override
    public CharitableDTO getCharitableDTO(long id) {
        CharitableDTO charitableDTO = null;
        Optional<NeedPeople> needPeople =  needPeopleRepository.findById(id);
        if(needPeople.isPresent()&& needPeople.get().getCharitable() != null){
            charitableDTO =  mapperFacade.map(needPeople.get().getCharitable(),CharitableDTO.class);
        }
        return charitableDTO;
    }

    @Override
    public List<CharitableDTO> getAllCharitableDTO() {
        final List<CharitableDTO> charitableDTOs = new ArrayList<>();
        charitableRepository.findAll().forEach(charitable -> {
            CharitableDTO  charitableDTO = mapperFacade.map(charitable,CharitableDTO.class);
            charitableDTOs.add(charitableDTO);
        });
        return charitableDTOs;
    }


    @Scheduled(fixedDelay = 86400000 )
    public void updateReserveToActive(){
        List<NeedPeople>  needPeopleReservedList = needPeopleRepository.findByState(RecordState.RESERVED);
        needPeopleReservedList.forEach(needPeople -> {
            needPeople.setState(RecordState.ACTIVE);
            needPeopleRepository.save(needPeople);
        });
    }

}

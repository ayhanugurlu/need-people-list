package com.au.meb.service;

import com.au.meb.dto.NeedPeopleDTO;

import java.util.List;

/**
 * Created by Ayhan.Ugurlu on 16/10/2018
 */
public interface NeedPeopleService {

    void save(NeedPeopleDTO needPeople);

    List<NeedPeopleDTO> list();

    void complete(long id);
}

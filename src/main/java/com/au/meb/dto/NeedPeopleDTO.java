package com.au.meb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ayhanugurlu on 10/15/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NeedPeopleDTO {


    private long id;

    private String name;

    private String surname;

    private String needs;



}

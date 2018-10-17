package com.au.meb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String address;

    private String needs;



}

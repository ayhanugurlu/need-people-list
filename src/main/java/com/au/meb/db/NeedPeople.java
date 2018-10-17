package com.au.meb.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ayhanugurlu on 10/15/18.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NeedPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private String address;

    private String needs;

    //active true
    private boolean state;



}

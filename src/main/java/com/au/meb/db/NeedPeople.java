package com.au.meb.db;

import com.au.meb.common.Gender;
import com.au.meb.common.Size;
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

    private int age;

    private String schoolName;

    private String address;

    private Gender gender;

    private Size size;

    private int footSize;

    private String needs;

    //active true
    private boolean state;



}

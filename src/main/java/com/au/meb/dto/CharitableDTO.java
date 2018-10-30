package com.au.meb.dto;

import lombok.Data;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
@Data
public class CharitableDTO {
    private long id;

    private String name;

    private String surname;

    private String gsm;

    private String mail;

    private boolean hasStudent;
}

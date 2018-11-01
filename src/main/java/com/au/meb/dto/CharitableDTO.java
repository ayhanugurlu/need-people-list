package com.au.meb.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
@Data
@Builder
public class CharitableDTO {
    private long id;

    private String tck;

    private String name;

    private String surname;

    private String gsm;

    private String mail;

    private boolean hasStudent;
}

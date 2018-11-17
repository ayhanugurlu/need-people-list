package com.au.meb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

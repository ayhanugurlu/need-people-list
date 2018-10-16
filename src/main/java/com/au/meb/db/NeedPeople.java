package com.au.meb.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

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
    private long id;

    private String name;

    private String surname;

    private String needs;



}

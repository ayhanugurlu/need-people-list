package com.au.meb.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ayhanugurlu on 10/30/18.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Charitable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String tck;

    private String name;

    private String surname;

    private String gsm;

    private String mail;

    private boolean hasStudent;

    @OneToMany(fetch = FetchType.EAGER,targetEntity = NeedPeople.class)
    private Set<NeedPeople> needPeople;

    @OneToMany(fetch = FetchType.EAGER,targetEntity = NeedSchool.class)
    private Set<NeedSchool> needSchool;




}

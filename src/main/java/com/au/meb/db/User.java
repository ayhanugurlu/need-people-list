package com.au.meb.db;

import com.au.meb.common.AuthrityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique=true)
    private String mail;
    private String password;
    private AuthrityType authority;

}

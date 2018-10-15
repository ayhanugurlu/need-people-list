package com.au.meb.dto;

import com.au.meb.common.AuthrityType;
import lombok.Data;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@Data
public class UserDTO {

    private String mail;
    private AuthrityType authority;

}

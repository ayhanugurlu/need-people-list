package com.au.meb.service;


import com.au.meb.common.exception.AuthenticationException;
import com.au.meb.dto.UserDTO;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
public interface UserService {

    UserDTO login(String username, String password) throws AuthenticationException;

    void save(UserDTO user);


}

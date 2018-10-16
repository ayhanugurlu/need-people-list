package com.au.meb.service;

import com.au.meb.common.AuthrityType;
import com.au.meb.common.exception.AuthenticationException;
import com.au.meb.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDTO login(String username, String password) throws AuthenticationException {
        UserDTO userDTO = new UserDTO();
        userDTO.setMail("aaaa");
        userDTO.setAuthority(AuthrityType.ADMIN);
        return userDTO;
    }
}

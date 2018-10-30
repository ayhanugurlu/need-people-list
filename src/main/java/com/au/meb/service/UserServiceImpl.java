package com.au.meb.service;

import com.au.meb.common.exception.AuthenticationException;
import com.au.meb.db.User;
import com.au.meb.db.UserRepository;
import com.au.meb.dto.UserDTO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by ayhanugurlu on 10/13/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Qualifier("serviceMapper")
    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public UserDTO login(String username, String password) throws AuthenticationException {

        User user=  userRepository.findByMail(username);
        if(user != null){
            if(!user.getPassword().equals(password)){
                throw new AuthenticationException();
            }else{
                return mapperFacade.map(user,UserDTO.class);

            }
        }else{
            throw new AuthenticationException();
        }


    }

    @Override
    public void save(UserDTO userDto) {
        User user = mapperFacade.map(userDto,User.class);
        userRepository.save(user);
    }

}

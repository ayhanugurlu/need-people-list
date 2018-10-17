package com.au.meb.common.listener;


import com.au.meb.common.AuthrityType;
import com.au.meb.db.User;
import com.au.meb.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * Created by Ayhan.Ugurlu on 17/10/2018
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    UserRepository userRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        User user = User.builder().mail("ayhanugurlu@gmail.com").authority(AuthrityType.ADMIN).password("password").build();
        userRepository.save(user);
    }
}

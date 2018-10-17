package com.au.meb.db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ayhanugurlu on 10/13/18.
 */

public interface UserRepository extends JpaRepository<User,Long> {
    User findByMail(String email);
}

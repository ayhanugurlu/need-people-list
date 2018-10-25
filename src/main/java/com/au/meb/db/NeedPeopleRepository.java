package com.au.meb.db;

import com.au.meb.common.RecordState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ayhanugurlu on 10/15/18.
 */
public interface NeedPeopleRepository extends JpaRepository<NeedPeople,Long>{

        List<NeedPeople> findByState(RecordState state);
}

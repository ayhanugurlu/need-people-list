package com.au.meb.db;

import com.au.meb.common.Gender;
import com.au.meb.common.RecordState;
import com.au.meb.common.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ayhanugurlu on 10/15/18.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NeedSchool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String schoolName;

    private String address;

    private String needs;

    private RecordState state;

    @ManyToOne(fetch = FetchType.EAGER,optional = true)
    @JoinColumn(name = "needSchool")
    private Charitable charitable;

}

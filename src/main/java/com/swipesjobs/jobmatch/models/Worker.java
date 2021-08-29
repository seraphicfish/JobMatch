package com.swipesjobs.jobmatch.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Worker {
    private Integer rating;
    private Boolean isActive;
    private List<String> certificates;
    private List<String> skills;
    private Address jobSearchAddress;
    private String phone;
    private String email;

    private Name name;
    private String transportation;
    private boolean hasDriversLicense;
    private List<AvailableDay> availability;
    private Integer age;
    private String guid;
    private Integer userId;

}

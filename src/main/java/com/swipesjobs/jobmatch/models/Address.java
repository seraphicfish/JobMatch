package com.swipesjobs.jobmatch.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private String unit;
    private Integer maxJobDistance;
    private String longitude;
    private String latitude;
}
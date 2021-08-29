package com.swipesjobs.jobmatch.models;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class Job {
    private boolean driverLicenseRequired;
    private List<String> requiredCertificates;
    private Location location;
    private String billRate;
    private Integer workersRequired;
    private OffsetDateTime startDate;
    private String about;
    private String jobTitle;
    private String company;
    private String guid;
    private Integer jobId;


}
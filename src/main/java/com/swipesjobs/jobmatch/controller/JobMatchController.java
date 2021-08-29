package com.swipesjobs.jobmatch.controller;


import com.swipesjobs.jobmatch.models.Job;
import com.swipesjobs.jobmatch.services.JobMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobMatchController {

    @Autowired
    JobMatchService jobMatchService;

    @GetMapping("/matchedJobs/{userId}")
    public ResponseEntity<List<Job>> getMatchedJobs(@PathVariable("userId") Integer userId) throws Exception {

        List<Job> matchedJobList = new ArrayList<>();

        try {

            matchedJobList = jobMatchService.getMatchedJobs(userId);

        } catch (Exception e){

            throw new Exception("Can't find matched jobs");
        }

        return new ResponseEntity<List<Job>>(matchedJobList, HttpStatus.OK);
    }
}


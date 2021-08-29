package com.swipesjobs.jobmatch.services;

import com.swipesjobs.jobmatch.models.Job;
import com.swipesjobs.jobmatch.models.Worker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    //Get all the users

    /**
     * get the worker list
     * @return worker list
     */
    public List<Worker> getWorkers(){

        List<Worker> workerList = new ArrayList<>();

        log.info("Retrieve the workers");
        ResponseEntity<Worker[]> responseEntity =
                restTemplate.exchange("https://test.swipejobs.com/api/workers", HttpMethod.GET, null, Worker[].class);

        Worker[] workers = responseEntity.getBody();

        for(int i= 0; i<workers.length; i++){

            log.info(workers[i].toString());

            workerList.add(workers[i]);

        }

        return workerList;

    }

    /**
     * get the job list
     * @return Job list
     */
    public List<Job> getJobs(){

        List<Job> jobList = new ArrayList<>();

        log.info("Retrieve the posts");

        ResponseEntity<Job[]> responseEntity =
                restTemplate.exchange("https://test.swipejobs.com/api/jobs", HttpMethod.GET, null, Job[].class);

        Job[] jobs = responseEntity.getBody();

        for(int i= 0; i<jobs.length; i++){

            jobList.add(jobs[i]);

        }

        return jobList;

    }
}

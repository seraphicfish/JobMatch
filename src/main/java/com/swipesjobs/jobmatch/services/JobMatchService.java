package com.swipesjobs.jobmatch.services;

import com.swipesjobs.jobmatch.models.Job;
import com.swipesjobs.jobmatch.models.Worker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// this class is used to return three most matched jobs
@Service
@Log4j2
public class JobMatchService {

    private static final double R = 6371; //Radius of the earth in km

    @Autowired
    private RestTemplateService restTemplateService;

    public List<Job> getMatchedJobs(Integer userId){

        List<Job> jobList = new ArrayList<>();

        List<Job> jobFileter =  new ArrayList<>();

        List<Worker> workers = new ArrayList<>();

        List<Job> jobs = new ArrayList<>();

        Map<Job, Integer> map = new HashMap<>();

        log.info("Get the works");
        workers = restTemplateService.getWorkers();

        log.info("Get the jobs");
        jobs = restTemplateService.getJobs();


        if(workers == null || workers.isEmpty()|| jobs ==null || jobs.isEmpty()) {

            return jobList;
        }

        log.info("find the work in the returned works");
        Worker worker = workers.stream()
                .filter(w -> w.getUserId().equals(userId))
                .findAny()
                .orElse(null);

        if(worker == null) {return jobList;}

        log.info("Filter the job which required driver license if the work doesn't have license");
        if(!worker.isHasDriversLicense()) {

            jobFileter = jobs.stream()
                    .filter(job-> !job.isDriverLicenseRequired())
                    .collect(Collectors.toList());
        } else {

            jobFileter = jobs;
        }

        log.info("Filter the jobs which is ouf of max work distance if the work has max work distance");
        for(Job job: jobFileter){

            if( worker.getJobSearchAddress()!=null && worker.getJobSearchAddress().getMaxJobDistance() != null){

                if(!isInWorkDistance(worker, job)){
                    continue;
                }

            }

            jobList.add(job);
        }

        if(jobList.size()<=3){

            return jobList;
        }

        // return the three most matched jobs
        for(Job job: jobList){

            map.put(job, numberOfMatchedCertificate(worker.getCertificates(), job.getRequiredCertificates()));
        }

        PriorityQueue<Job> pq = new PriorityQueue<>((a, b)-> map.get(a)-map.get(b));

        pq.addAll(map.keySet());

        List<Job> result = new ArrayList<>();

        for(int i =0; i<3; i++){

            result.add(pq.remove());
        }

        return result;
    }

    /**
     * find the number of matched certificate
     * @param list1 List of string
     * @param list2 List of string
     * @return Integer number of the matched certificate
     */
    private Integer numberOfMatchedCertificate(List<String> list1, List<String> list2){

        List<String> list = list1;

        list1.retainAll(list2);

        return list1.size();

    }

    /**
     * Check if the job distance is out of the work's max work distance
     * @param worker Worker
     * @param job  Job
     * @return boolean
     */
    private boolean isInWorkDistance(Worker worker, Job job){

        Double lat1 = Double.parseDouble(worker.getJobSearchAddress().getLatitude());
        Double lat2 = Double.parseDouble(job.getLocation().getLatitude());
        Double lon1 = Double.parseDouble(worker.getJobSearchAddress().getLongitude());
        Double lon2 = Double.parseDouble(job.getLocation().getLongitude());

        Double distanceLat = degreeToRadian (lat2-lat1);

        Double distanceLon = degreeToRadian (lon2- lon1);


        Double ans = Math.sin(distanceLat/2) * Math.sin(distanceLat/2) +
                Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) *
                        Math.sin(distanceLon/2) * Math.sin(distanceLon/2);

        Double result = 2 * Math.atan2(Math.sqrt(ans), Math.sqrt(1-ans));

        Double distance = R*result;

        if(distance > worker.getJobSearchAddress().getMaxJobDistance()){

            return false;

        } else {

            return true;
        }
    }

    private Double degreeToRadian (Double degree){

        return degree*(Math.PI/180);
    }
}

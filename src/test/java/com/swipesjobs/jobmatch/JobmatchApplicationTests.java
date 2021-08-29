package com.swipesjobs.jobmatch;

import com.swipesjobs.jobmatch.models.Job;
import com.swipesjobs.jobmatch.models.Worker;
import com.swipesjobs.jobmatch.services.JobMatchService;
import com.swipesjobs.jobmatch.services.RestTemplateService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.swipesjobs.jobmatch")
class JobmatchApplicationTests {

	@Autowired
	JobMatchService jobMatchService;

	@MockBean
	RestTemplateService restTemplateService;


	@Test
	public void testReturn_job_requiredLicense_only_to_user_with_license(){

		when(restTemplateService.getJobs()).thenReturn(createJobList());
		when(restTemplateService.getWorkers()).thenReturn(createWorkList());

		List<Job> jobsWithLicense = new ArrayList<>();
		List<Job> jobsWithoutLicense = new ArrayList<>();
		jobsWithLicense = jobMatchService.getMatchedJobs(0);
		jobsWithoutLicense = jobMatchService.getMatchedJobs(1);
		assertTrue(jobsWithLicense.size()==1);
		assertTrue(jobsWithoutLicense.size()==0);
	}

	public List<Job> createJobList(){

		List<Job> jobList = new ArrayList<>();
		List<String> requiredCertificates = new ArrayList<>();

		requiredCertificates.add("Outstanding Innovator");
		requiredCertificates.add("Outside the Box Thinker");
		requiredCertificates.add("Marvelous Multitasker");
		requiredCertificates.add("Outstanding Memory Award");
		requiredCertificates.add("Excellence in Organization");
		Job job1 = new Job();
		job1.setDriverLicenseRequired(true);
		job1.setRequiredCertificates(requiredCertificates);

		jobList.add(job1);

		return jobList;

	}

	public List<Worker> createWorkList(){

		List<Worker> workerList = new ArrayList<>();
		Worker worker1 = new Worker();
		Worker worker2 = new Worker();
		worker1.setHasDriversLicense(true);
		worker1.setUserId(0);

		worker2.setHasDriversLicense(false);
		worker2.setUserId(1);

		workerList.add(worker1);
		workerList.add(worker2);

		return workerList;
	}
}

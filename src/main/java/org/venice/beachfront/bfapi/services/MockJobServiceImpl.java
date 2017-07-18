package org.venice.beachfront.bfapi.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.venice.beachfront.bfapi.model.Confirmation;
import org.venice.beachfront.bfapi.model.Job;

public class MockJobServiceImpl implements JobService {

	@Override
	public Job createJob() {
		return new Job(
				"job-id-123", 
				"Job Name Foo Bar", 
				"in-progress", 
				"created-by-456", 
				DateTime.now(), 
				"ndwi", 
				"1.0", 
				null, 
				"Sentinel-2", 
				DateTime.now(), 
				"S2-ABC-1234", 
				null, 
				"PL_API_1234567");
	}

	@Override
	public List<Job> getJobs() {
		Job job = this.createJob();
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(job);
		return jobs;
	}

	@Override
	public Job getJob(String jobId) {
		return new Job(
				jobId, 
				"Job Name Foo Bar", 
				"in-progress", 
				"created-by-456", 
				DateTime.now(), 
				"ndwi", 
				"1.0", 
				null, 
				"Sentinel-2", 
				DateTime.now(), 
				"S2-ABC-1234", 
				null, 
				"PL_API_1234567");
	}

	@Override
	public Confirmation deleteJob(Job job) {
		if (job.getJobId().equals("doNotDelete")) {
			return new Confirmation(job.getJobId(), false);
		}
		return new Confirmation(job.getJobId(), true);
	}

}

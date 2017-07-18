package org.venice.beachfront.bfapi.services;

import java.util.List;

import org.venice.beachfront.bfapi.model.Confirmation;
import org.venice.beachfront.bfapi.model.Job;

public interface JobService {
	public Job createJob();
	public List<Job> getJobs();
	public Job getJob(String jobId);
	public Confirmation deleteJob(Job job);
}

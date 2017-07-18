package org.venice.beachfront.bfapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.venice.beachfront.bfapi.model.Confirmation;
import org.venice.beachfront.bfapi.model.Job;
import org.venice.beachfront.bfapi.services.JobService;

/**
 * Main controller class for the Job CRUD endpoints.
 * 
 * @version 1.0
 */
@Controller
public class JobCrudController {
	private JobService jobService;
	
	@Autowired
	public JobCrudController(JobService jobService) {
		this.jobService = jobService;
	}
	
	@RequestMapping(
	        path="/job",
	        method=RequestMethod.GET,
	        produces={"application/json"})
	@ResponseBody
	public List<Job> listJobs() {
		return this.jobService.getJobs();
	}
	
	@RequestMapping(
			path="/job/{id}",
			method=RequestMethod.GET,
			produces={"application/json"})
	@ResponseBody
	public Job getJobById(@PathVariable("id") String id) {
		return this.jobService.getJob(id);
	}
	
	@RequestMapping(
			path="/job/{id}",
			method=RequestMethod.DELETE,
			produces={"application/json"})
	@ResponseBody
	public Confirmation deleteJob(@PathVariable("id") String id) {
		Job job = this.jobService.getJob(id);
		return this.jobService.deleteJob(job);
	}
}

package org.venice.beachfront.bfapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.venice.beachfront.bfapi.services.JobService;
import org.venice.beachfront.bfapi.services.MockJobServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class BfApiConfig {
	@Profile("prototype")
	@Bean
	public JobService getPrototypeMockJobService() {
		return new MockJobServiceImpl();
	}
	
	@Bean
	public ObjectMapper getJacksonObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		return mapper;
	}
}

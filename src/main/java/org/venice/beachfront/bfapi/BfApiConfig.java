package org.venice.beachfront.bfapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.venice.beachfront.bfapi.model.Environment;
import org.venice.beachfront.bfapi.services.IABrokerPassthroughService;
import org.venice.beachfront.bfapi.services.IABrokerPassthroughServiceImpl;
import org.venice.beachfront.bfapi.services.JobService;
import org.venice.beachfront.bfapi.services.JobServiceProtoImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class BfApiConfig {
	@Bean
	public ObjectMapper getJacksonObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		return mapper;
	}
	
	@Bean
	public Environment getEnvironmentConfiguration(@Value("${IA_BROKER}") String iaBroker) throws MalformedURLException  {
		URL iaBrokerBaseUrl = new URL(iaBroker);
		
		return new Environment() {
			public URL getIABrokerBaseUrl() {
				return iaBrokerBaseUrl;
			}
		};
	}
	
	@Bean
	public IABrokerPassthroughService getIABrokerPassthroughService() {
		return new IABrokerPassthroughServiceImpl();
	}
	
	@Bean 
	public ExecutorService getExecutor(@Value("${concurrent.threads}") int threads) {
		return Executors.newFixedThreadPool(threads);
	}

	@Profile("prototype")
	@Bean
	public JobService getMockJobPrototypeService() {
		return new JobServiceProtoImpl();
	}
}

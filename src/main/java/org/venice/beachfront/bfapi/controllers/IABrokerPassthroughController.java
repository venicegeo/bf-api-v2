package org.venice.beachfront.bfapi.controllers;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.venice.beachfront.bfapi.services.IABrokerPassthroughService;
import org.venice.beachfront.bfapi.services.IABrokerPassthroughService.Response;

/**
 * Main controller class for the passthrough to bf-ia-broker.
 * 
 * @version 1.0
 */
@Controller
public class IABrokerPassthroughController {
	private IABrokerPassthroughService iaBrokerPassthroughService;
	
	@Autowired
	public IABrokerPassthroughController(IABrokerPassthroughService service) {
		this.iaBrokerPassthroughService = service;
	}
	
	@RequestMapping(path="/ia/**")
	@ResponseBody
	public CompletableFuture<ResponseEntity<byte[]>> passthrough(HttpServletRequest request) throws IOException {
		String iaURI = request.getRequestURI().substring(4); // Skip "/ia/"
		CompletableFuture<Response> future = this.iaBrokerPassthroughService.passthroughRequest(iaURI, request);

		return future.thenApply((Response response) -> {
			return this.buildResponseEntity(response);
		});
	}
	
	
	private ResponseEntity<byte[]> buildResponseEntity(Response response) {
		if (response.getThrowable() != null) {
			response.getThrowable().printStackTrace();
		}
				
		return new ResponseEntity<byte[]>(
				response.getBody(),
				response.getHeaders(),
				response.getStatusCode()
				);
	}
}
package com.tivit.api.controller;

import javax.jws.WebParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tivit.api.service.CommunicationService;

@RestController
@RequestMapping("/")
public class CommunicationController {
	
	@Value("${com.tivit.api.controller.communication.target}")
	private String address;
	
	@Autowired
	private CommunicationService communicationService;
	
	@GetMapping("transfers")
	public String transfers(@RequestParam("timeoutOne") Integer timeoutOne, @RequestParam("timeoutTwo") Integer timeoutTwo, 
			@RequestParam("repeat") Integer repeat) {
		String ret = "";
		int counter = repeat;
		
		while(counter > 0) {
			ret += communicationService.send(address, timeoutOne, timeoutTwo) + " | ";
			counter--;
		}
		
		return ret;
	}
}

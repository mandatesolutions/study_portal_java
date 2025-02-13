package com.studyportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/studyportal/commonlogin")
@Slf4j
public class CommonLogin {

	  @GetMapping("/hello")
	    public String hello() {
	        return "Swagger is working!";
	    }
}



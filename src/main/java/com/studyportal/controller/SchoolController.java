package com.studyportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyportal.model.SchoolRegisterModel;
import com.studyportal.service.SchoolService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/studyportal/school")
@Slf4j
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Operation(summary = "Register School Api", description = "This API is used to register School.")
	@PostMapping("/schoolRegister")
	ResponseEntity<Object> schoolRegister(@Valid @RequestBody SchoolRegisterModel school) {
		log.info("***** Inside - UserController - userRegister *****");
		return schoolService.addSchool(school);
	}	

}

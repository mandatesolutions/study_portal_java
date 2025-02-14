package com.studyportal.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyportal.helper.ApiResponse;
import com.studyportal.model.LoginRequest;
import com.studyportal.service.CommonLoginService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/studyportal/commonlogin")
@Slf4j
public class CommonLoginController {

//	  @GetMapping("/hello")
//	    public String hello() {
//	        return "Swagger is working!";
//	    }
	
	private CommonLoginService loginService;

	public CommonLoginController(CommonLoginService loginService) {
		super();
		this.loginService = loginService;
	}

	@Operation(summary = "User Login API", description = "This API allows users to log in with their credentials.")
	@PostMapping("/login")
	ResponseEntity<ApiResponse<Map<String, Object>>> login(@Valid @RequestBody LoginRequest request) throws Exception {
		if (log.isInfoEnabled()) {
			log.info("***** Inside CommonLoginController - login *****");
		}
		return loginService.login(request);
	}
	
	
}



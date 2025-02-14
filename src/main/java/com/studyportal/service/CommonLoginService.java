package com.studyportal.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.studyportal.helper.ApiResponse;
import com.studyportal.model.LoginRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service
public interface CommonLoginService {
	
	abstract ResponseEntity<ApiResponse<Map<String, Object>>> login(@Valid LoginRequest loginRequest) throws Exception;

}

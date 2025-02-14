package com.studyportal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyportal.model.SchoolRegisterModel;

import jakarta.validation.Valid;

@Service
public interface SchoolService 
{
	public ResponseEntity<Object> addSchool(@Valid SchoolRegisterModel school); 
}

package com.studyportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyportal.entity.School;
import com.studyportal.helper.ApiResponse;
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
	
	@PostMapping("/register")
    @Operation(
        summary = "Register a new school",
        description = "This API allows the registration of a new school. The school must have a unique name."
    )
    public ResponseEntity<Object> registerSchool(@RequestBody School school) {
        return schoolService.registerSchool(school);
    }

    @GetMapping
    @Operation(
        summary = "Get all schools",
        description = "This API retrieves a list of all active schools registered in the system."
    )
    public ResponseEntity<Object> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a specific school by ID",
        description = "This API retrieves the details of a specific school using its ID. Returns 404 if the school is not found."
    )
    public ResponseEntity<ApiResponse<School>> getSchoolById(@PathVariable Long id) {
        return schoolService.getSchoolById(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing school",
        description = "This API updates the details of an existing school. The school must be active to be updated."
    )
    public ResponseEntity<Object> updateSchool(@PathVariable Long id, @RequestBody School updatedSchool) {
        return schoolService.updateSchool(id, updatedSchool);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Change school status to INACTIVE",
        description = "This API changes the status of a school to INACTIVE instead of deleting it. The school must be active to change the status."
    )
    public ResponseEntity<Object> deleteSchool(@PathVariable Long id) {
        return schoolService.deleteSchool(id);
    }

}

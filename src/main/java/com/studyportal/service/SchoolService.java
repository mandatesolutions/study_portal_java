package com.studyportal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyportal.entity.School;
import com.studyportal.helper.ApiResponse;
import com.studyportal.model.SchoolRegisterModel;

import jakarta.validation.Valid;

public interface SchoolService 
{
	ResponseEntity<Object> registerSchool(School school);

    ResponseEntity<Object> getAllSchools();

    ResponseEntity<ApiResponse<School>> getSchoolById(Long schoolId);

    ResponseEntity<Object> updateSchool(Long schoolId, School updatedSchool);

    ResponseEntity<Object> deleteSchool(Long schoolId);  // Changing status to INACTIVE instead of delete

}

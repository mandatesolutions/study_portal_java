package com.studyportal.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.studyportal.entity.AcademicYear;
import com.studyportal.helper.ApiResponse;

public interface AcademicYearService {
	
	    ResponseEntity<ApiResponse<AcademicYear>> createAcademicYear(String academicYearName, LocalDate startDate, LocalDate endDate);

	    ResponseEntity<ApiResponse<AcademicYear>> getAcademicYearById(Long academicYearId);

	    ResponseEntity<ApiResponse<AcademicYear>> getAcademicYearByName(String academicYearName);


}

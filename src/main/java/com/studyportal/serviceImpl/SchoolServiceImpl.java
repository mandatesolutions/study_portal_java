package com.studyportal.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyportal.entity.School;
import com.studyportal.helper.ApiResponse;
import com.studyportal.helper.CommonMessages;
import com.studyportal.helper.Enums.UserStatus;
import com.studyportal.model.SchoolRegisterModel;
import com.studyportal.repository.SchoolRepository;
import com.studyportal.service.SchoolService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

 // Check if the school is active before proceeding with operations
    private boolean isSchoolActive(School school) {
        return school.getStatus() == UserStatus.ACTIVE;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> registerSchool(School school) {
        try {
            if (schoolRepository.existsBySchoolName(school.getSchoolName())) {
                return ResponseEntity.badRequest().body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_ALREADY_EXISTS, null));
            }

            if (school.getUuid() == null) {
                school.setUuid(java.util.UUID.randomUUID().toString());
            }

            School savedSchool = schoolRepository.save(school);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(CommonMessages.SUCCESS, CommonMessages.SCHOOL_REGISTER_SUCCESS, savedSchool));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_REGISTRATION_FAILED, null));
        }
	    }
	
	    @Override
	    public ResponseEntity<Object> getAllSchools() {
	        List<School> schools = schoolRepository.findAll();
	        return ResponseEntity.ok(new ApiResponse<>(CommonMessages.SUCCESS, CommonMessages.SCHOOL_FETCH_SUCCESS, schools));
	    }
	
	    @Override
	    public ResponseEntity<ApiResponse<School>> getSchoolById(Long schoolId) {
	        Optional<School> school = schoolRepository.findById(schoolId);
	        return school.map(value -> ResponseEntity.ok
	        		(new ApiResponse<>(CommonMessages.SUCCESS, CommonMessages.SCHOOL_FETCH_SUCCESS, value)))
	                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_NOT_FOUND, null)));
	    }

	    @Override
	    @Transactional
	    public ResponseEntity<Object> updateSchool(Long schoolId, School updatedSchool) {
	        Optional<School> existingSchool = schoolRepository.findById(schoolId);
	        
	        if (existingSchool.isPresent()) {
	            School school = existingSchool.get();
	
	            // Check if the school is active before proceeding
	            if (!isSchoolActive(school)) {
	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_STATUS_INACTIVE, null));
	            }
	
	            school.setSchoolName(updatedSchool.getSchoolName());
	            school.setStatus(updatedSchool.getStatus());
	            school.setDistrict(updatedSchool.getDistrict());
	            school.setTaluka(updatedSchool.getTaluka());
	            
	            School savedSchool = schoolRepository.save(school);
	            return ResponseEntity.ok(new ApiResponse<>(CommonMessages.SUCCESS, CommonMessages.SCHOOL_UPDATE_SUCCESS, savedSchool));
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_NOT_FOUND, null));
	        }
	    }
	
	    @Override
	    @Transactional
	    public ResponseEntity<Object> deleteSchool(Long schoolId) {
	        try {
	            Optional<School> schoolOpt = schoolRepository.findById(schoolId);
	            
	            if (schoolOpt.isPresent()) {
	                School school = schoolOpt.get();
	
	                // Check if the school is active before proceeding
	                if (!isSchoolActive(school)) {
	                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_STATUS_INACTIVE, null));
	                }
	
	                school.setStatus(UserStatus.INACTIVE);  // Set status to INACTIVE
	                schoolRepository.save(school);
	                return ResponseEntity.ok(new ApiResponse<>(CommonMessages.SUCCESS, CommonMessages.SCHOOL_DELETE_SUCCESS, null));
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_NOT_FOUND, null));
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(CommonMessages.FAILED, CommonMessages.SCHOOL_DELETE_FAILED, null));
	        }
	    }
	}
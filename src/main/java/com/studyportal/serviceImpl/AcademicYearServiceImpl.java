package com.studyportal.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyportal.entity.AcademicYear;
import com.studyportal.helper.ApiResponse;
import com.studyportal.helper.CommonMessages;
import com.studyportal.repository.AcademicYearRepository;
import com.studyportal.service.AcademicYearService;

@Service
public class AcademicYearServiceImpl implements AcademicYearService{

	@Autowired
    private AcademicYearRepository academicYearRepository;

    @Override
    public ResponseEntity<ApiResponse<AcademicYear>> createAcademicYear(String academicYearName, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(CommonMessages.FAILED, "Start date cannot be after end date", null));
        }

        AcademicYear academicYear = new AcademicYear();
        academicYear.setAcademicYearName(academicYearName);
        academicYear.setStartDate(startDate);
        academicYear.setEndDate(endDate);

        try {
            AcademicYear savedAcademicYear = academicYearRepository.save(academicYear);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(CommonMessages.SUCCESS, "Academic Year created successfully", savedAcademicYear));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(CommonMessages.FAILED, "Error creating academic year", null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<AcademicYear>> getAcademicYearById(Long academicYearId) {
        return academicYearRepository.findById(academicYearId)
                .map(academicYear -> ResponseEntity.ok(new ApiResponse<>(CommonMessages.SUCCESS, "Academic Year fetched successfully", academicYear)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(CommonMessages.FAILED, "Academic Year not found", null)));
    }

    @Override
    public ResponseEntity<ApiResponse<AcademicYear>> getAcademicYearByName(String academicYearName) {
        AcademicYear academicYear = academicYearRepository.findByAcademicYearName(academicYearName);
        if (academicYear != null) {
            return ResponseEntity.ok(new ApiResponse<>(CommonMessages.SUCCESS, "Academic Year fetched successfully", academicYear));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(CommonMessages.FAILED, "Academic Year not found", null));
        }
    }

	
}

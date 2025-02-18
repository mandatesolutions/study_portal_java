package com.studyportal.controller;

import com.studyportal.entity.AcademicYear;
import com.studyportal.helper.ApiResponse;
import com.studyportal.service.AcademicYearService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/studyportal/academic-years")
public class AcademicYearController {

    @Autowired
    private AcademicYearService academicYearService;

    @Operation(summary = "Create a new academic year", description = "Creates a new academic year with a name, start date, and end date.")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AcademicYear>> createAcademicYear(
            @Parameter(description = "Academic Year name (e.g., 2024-2025)")
            @RequestParam String academicYearName,
            @Parameter(description = "Start date of the academic year")
            @RequestParam LocalDate startDate,
            @Parameter(description = "End date of the academic year")
            @RequestParam LocalDate endDate) {
        
        return academicYearService.createAcademicYear(academicYearName, startDate, endDate);
    }

    @Operation(summary = "Get academic year by ID", description = "Fetches an academic year by its ID.")
    @GetMapping("/{academicYearId}")
    public ResponseEntity<ApiResponse<AcademicYear>> getAcademicYearById(@Parameter(description = "ID of the academic year") @PathVariable Long academicYearId) {
        return academicYearService.getAcademicYearById(academicYearId);
    }

    @Operation(summary = "Get academic year by name", description = "Fetches an academic year by its name (e.g., 2024-2025).")
    @GetMapping("/by-name")
    public ResponseEntity<ApiResponse<AcademicYear>> getAcademicYearByName(
            @Parameter(description = "Name of the academic year (e.g., 2024-2025)") @RequestParam String academicYearName) {
        return academicYearService.getAcademicYearByName(academicYearName);
    }
}

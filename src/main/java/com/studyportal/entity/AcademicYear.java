package com.studyportal.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "academic_year")
@Getter
@Setter
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long academicYearId;

    private String academicYearName;  // e.g., "2024-2025"

    private LocalDate startDate;  // Start date of the academic year
    private LocalDate endDate;    // End date of the academic year

}

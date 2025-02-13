package com.studyportal.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;

    private String classroomName;

    private String gradeLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;  // Linking to the AcademicYear entity

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private List<Student> students;  // Students in the classroom

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private List<Teacher> teachers;  // Teachers for the classroom

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "classroom_subject", 
        joinColumns = @JoinColumn(name = "classroom_id"), 
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<SchoolSubject> subjects;  // Subjects taught in the classroom
    
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

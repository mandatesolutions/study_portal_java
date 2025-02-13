package com.studyportal.entity;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studyportal.helper.Enums.ApprovalStatus;
import java.time.LocalDateTime;

@Entity
@Table(name = "teachers")
@Setter
@Getter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "common_login_id")
    private CommonLogin commonLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassRoom classroom;  // Each teacher belongs to one classroom

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "teacher_subject", 
        joinColumns = @JoinColumn(name = "teacher_id"), 
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<SchoolSubject> subjects;  // Subjects taught by the teacher

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private String address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId")
    private District district;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "talukaId")
    private Taluka taluka;

    @JsonIgnore
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    public Teacher() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString(); // Auto-generate for new records
        }
    }
}

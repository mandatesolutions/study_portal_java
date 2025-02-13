package com.studyportal.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studyportal.helper.Enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String schoolName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taluka_id")
    private Taluka taluka;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<ClassRoom> classrooms;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<SchoolSubject> subjects;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;  // Adding status field
    
    @JsonIgnore
	@Column(unique = true, nullable = false, updatable = false)
	private String uuid;

	public School() {
		if (this.uuid == null) {
			this.uuid = UUID.randomUUID().toString(); // Auto-generate for new records
		}
		
	}
	@OneToOne
    @JoinColumn(name = "common_login_id")
	private CommonLogin commonLogin;  // Link to CommonLogin for supervisor login details

	@JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}




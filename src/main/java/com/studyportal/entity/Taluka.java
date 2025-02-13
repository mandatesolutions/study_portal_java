package com.studyportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Taluka {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long talukaId;

	private String talukaName;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "districtId")
	private District district;
}

package com.studyportal.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class SchoolRegisterModel {
	
	@NotEmpty(message = "schoolName name should not be Empty")
	private String schoolName;


	private long districtId;
	
	private String mobileNumber;

	private String role;

	private long talukaId;
}

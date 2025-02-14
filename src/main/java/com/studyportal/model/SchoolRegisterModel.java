package com.studyportal.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class SchoolRegisterModel {
	
	@NotEmpty(message = "schoolName name should not be Empty")
	private String schoolName;

	@NotEmpty(message = "User password should not be empty")
	private String userPassword;

	private long districtId;
	
	private String mobileNumber;

//	@Pattern(regexp = "ROLE_USER|ROLE_ADMIN|ROLE_SUPER_ADMIN", message = "Role must be either ROLE_USER || ROLE_ADMIN || ROLE_SUPER_ADMIN")
	private String role;

	private long talukaId;

}

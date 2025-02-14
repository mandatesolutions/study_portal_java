package com.studyportal.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyportal.model.SchoolRegisterModel;
import com.studyportal.service.SchoolService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchoolServiceImpl implements SchoolService {@Override
	public ResponseEntity<Object> addSchool(@Valid SchoolRegisterModel school) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ResponseEntity<Object> addSchool(@Valid SchoolRegisterModel school) {
//		log.info("***** Inside - SchoolServiceImpl  - addUser *****");
//		Map<Object, Object> response = new HashMap<>();
//		/* get district details */
//		District diDetails = districtRepo.findById(userDetails.getDistrictId())
//				.orElseThrow(() -> new ResourceNotFoundException(
//						"District with the Id" + userDetails.getDistrictId() + " not found"));
//		Taluka talukaDetails = talukaRepo.findById(userDetails.getTalukaId()).orElseThrow(
//				() -> new ResourceNotFoundException("Taluka with the Id" + userDetails.getTalukaId() + " not found"));
//		User userData = userRepo.findFirstByUserEmail(userDetails.getUserEmail());
//		User userMData = userRepo.findByUserMobileNumber(userDetails.getUserMobileNumber());
//		if (userMData != null) {
//			response.put(CommonMessages.STATUS, MSG.FAILED);
//			response.put(CommonMessages.MESSAGE, MSG.USER_REGISTER_MOBILE_NO_FAILED);
//			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//		}
//
//		if (userData == null || userData.getUserEmail() == null || userData.getUserEmail().isEmpty()
//				|| !userData.getUserEmail().equals(userDetails.getUserEmail())) {
//			User user = new User();
//			user.setUserFirstName(userDetails.getUserFirstName());
//			user.setUserLastName(userDetails.getUserLastName());
//			user.setUserEmail(userDetails.getUserEmail());
//			user.setUserDateOfBirth(userDetails.getUserDateOfBirth());
//			user.setUserGender(userDetails.getUserGender());
//			user.setUserRole(userDetails.getUserRole());
//			user.setUserpassword(encoder.encode(userDetails.getUserPassword()));
//			user.setUserStatus("ACTIVE");
//			user.setUserAddress(userDetails.getUserAddress());
//			user.setDistrict(diDetails);
//			user.setUserMobileNumber(userDetails.getUserMobileNumber());
//			user.setTaluka(talukaDetails);
//			user.setSchoolName(userDetails.getSchoolName());
//			user.setClassName(userDetails.getClassName());
//			user.setHobbies(userDetails.getHobbies());
//			// add org
//			// Check if the organization already exists
////			Organisation existingOrg = organisationRepo.findByOrgName(userDetails.getOrganisationName());
////			if (existingOrg != null) {
////				user.setOrg(existingOrg);
////			} else {
////				Organisation newOrg = new Organisation();
////				newOrg.setOrgName(userDetails.getOrganisationName());
////				organisationRepo.save(newOrg);
////				user.setOrg(newOrg);
////			}
//			userRepo.save(user);
//
//			response.put("status", CommonMessages.SUCCESS);
//			response.put("message", MSG.USER_REGISTARTION_SUCCESSFUL);
//			return new ResponseEntity<>(response, HttpStatus.CREATED);
//		} else {
//			response.put("status", MSG.FAILED);
//			response.put("message", MSG.USER_REGISTARTION_FAILED);
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//	}

}

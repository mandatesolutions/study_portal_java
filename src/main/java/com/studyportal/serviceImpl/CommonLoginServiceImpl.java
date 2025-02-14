package com.studyportal.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studyportal.entity.CommonLogin;
import com.studyportal.helper.ApiResponse;
import com.studyportal.helper.CommonMessages;
import com.studyportal.helper.Enums.UserStatus;
import com.studyportal.model.LoginRequest;
import com.studyportal.helper.JwtHelper;
import com.studyportal.repository.CommonLoginRepository;
import com.studyportal.service.CommonLoginService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonLoginServiceImpl implements CommonLoginService {
	
	private CommonLoginRepository loginRepository;
	private PasswordEncoder passwordEncoder;
	private JwtHelper jwtHelper;
	
	public CommonLoginServiceImpl(CommonLoginRepository loginRepository,JwtHelper jwtHelper,
			PasswordEncoder passwordEncoder) {
		super();
		
		this.loginRepository = loginRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtHelper=jwtHelper;
	}
	
	@Override
	@Transactional
	public ResponseEntity<ApiResponse<Map<String, Object>>> login(LoginRequest request) {
		if (log.isInfoEnabled()) {
			log.info("***** Inside CommonLoginServiceImpl - login *****");
		}
		Map<String, Object> response = new HashMap<>();
		ApiResponse<Map<String, Object>> resp = new ApiResponse<>();
		// Fetch user details based on email
		Optional<CommonLogin> user = loginRepository.findByEmail(request.getEmail());
		if (!user.isPresent()) {
			resp.setStatus(CommonMessages.FAILED);
			resp.setMessage(CommonMessages.CL_EMAIL_NF);
			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
		}

		// Check if the user is active
		if (UserStatus.IN_ACTIVE.equals(user.get().getStatus())) {
			resp.setStatus(CommonMessages.FAILED);
			resp.setMessage("User is not active.");
			return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
		}

		String role = user.get().getRole().getRoleName();
		// Check if password matches
		if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
			resp.setStatus(CommonMessages.FAILED);
			resp.setMessage(CommonMessages.CL_PASSWORD_NV);
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		}

		// Generate JWT Token
		String jwtToken = jwtHelper.generateToken(user.get());

		// Convert role to user-friendly format
		String formattedRole = role.replace("ROLE_", "").replace("-", " ").toUpperCase();

		// Prepare the response map
		resp.setStatus(CommonMessages.SUCCESS);

		// Prepare the response map
//		if (role.equals("ROLE_SUP-ADMIN") || role.equals("ROLE_ADMIN") || role.equals("ROLE_DCPO")
//				|| role.equals("ROLE_DEPUTY-COMMISSIONER")) {
//			resp.setStatus(CommonMessages.SUCCESS);
//			resp.setMessage(String.format("%s login successfully", formattedRole));
//			response.put("userId", user.get().getUserId());
//			response.put("email", user.get().getEmail());
//			response.put("role", role); // Include role in the response
//			response.put("token", jwtToken);
//			resp.setData(response);
//			return new ResponseEntity<>(resp, HttpStatus.OK);
//		}

		response.put("userId", user.get().getUserId());
		response.put("email", user.get().getEmail());
		response.put("role", role);
		response.put("token", jwtToken);
		response.put("uuid", user.get().getUuid());

		resp.setStatus(CommonMessages.SUCCESS);
		resp.setMessage(String.format("%s login successfully", formattedRole));
		resp.setData(response);
	
       return new ResponseEntity<>(resp, HttpStatus.OK);
}
}
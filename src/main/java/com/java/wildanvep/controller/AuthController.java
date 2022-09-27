package com.java.wildanvep.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.wildanvep.exception.BadRequestException;
import com.java.wildanvep.repository.UserRepository;
import com.java.wildanvep.request.LoginRequest;
import com.java.wildanvep.request.RefreshTokenRequest;
import com.java.wildanvep.service.JwtUserDetailsService;
import com.java.wildanvep.util.JwtTokenUtil;
import com.java.wildanvep.util.ResponseUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	protected final Log logger = LogFactory.getLog(getClass());

	final UserRepository userRepository;
	final AuthenticationManager authenticationManager;
	final JwtUserDetailsService userDetailsService;
	final JwtTokenUtil jwtTokenUtil;

	public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager,
			JwtUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			if (auth.isAuthenticated()) {
				logger.info("Logged In");
				UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
				String token = jwtTokenUtil.generateToken(userDetails);
				responseMap.put("error", false);
				responseMap.put("message", "Logged In");
				responseMap.put("token", token);

				return ResponseUtil.generateResponseSuccess(responseMap);
			} else {
				responseMap.put("error", true);
				responseMap.put("message", "Invalid Credentials");
				return ResponseEntity.status(401).body(responseMap);
			}
		} catch (DisabledException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", "User is disabled");
			return ResponseEntity.status(500).body(responseMap);
		} catch (BadCredentialsException e) {
			responseMap.put("error", true);
			responseMap.put("message", "Invalid Credentials");
			return ResponseEntity.status(401).body(responseMap);
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", "Something went wrong");
			return ResponseEntity.status(500).body(responseMap);
		}
	}

	@PostMapping("refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest data) {

		String username = jwtTokenUtil.getUsernameFromToken(data.getRefreshToken());

		if (!data.getUsername().equals(username)) {
			throw new BadRequestException("Token & Username did not match!");
		}

		String refreshedToken = jwtTokenUtil.generateRefreshToken(username);

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("tokenType", "Bearer");
		responseMap.put("token", refreshedToken);

		return ResponseEntity.ok(responseMap);
	}

	@PostMapping("logout")
	public ResponseEntity<?> logout(Authentication authentication) {

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("username", authentication.getName());
		responseMap.put("token", null);

		return ResponseEntity.ok(responseMap);

	}
}

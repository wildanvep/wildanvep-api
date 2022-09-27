package com.java.wildanvep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.wildanvep.entity.User;
import com.java.wildanvep.request.UserRequest;
import com.java.wildanvep.request.UserSearchRequest;
import com.java.wildanvep.response.SearchResponse;
import com.java.wildanvep.service.UserService;
import com.java.wildanvep.util.ResponseUtil;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public SearchResponse<User> searchUser(UserSearchRequest request) {

		SearchResponse<User> response = userService.searchUser(request);
		return response;
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {

		User user = userService.getUser(username);
		return ResponseUtil.generateResponseSuccess(user);
	}

	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody UserRequest request, Authentication authentication) {

		User user = userService.createUser(request, authentication.getName());
		return ResponseUtil.generateResponseSuccess(user);
	}

	@PutMapping("/{username}")
	public ResponseEntity<User> updateUser(@RequestBody UserRequest request, @PathVariable("username") String username,
			Authentication authentication) {

		request.setUserName(username);

		User user = userService.updateUser(request, authentication.getName());
		return ResponseUtil.generateResponseSuccess(user);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username, Authentication authentication) {

		User user = userService.deleteUser(username, authentication.getName());
		return ResponseUtil.generateResponseSuccess(user);
	}

}

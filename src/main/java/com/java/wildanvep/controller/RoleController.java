package com.java.wildanvep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.wildanvep.entity.Role;
import com.java.wildanvep.request.RoleRequest;
import com.java.wildanvep.request.RoleSearchRequest;
import com.java.wildanvep.response.SearchResponse;
import com.java.wildanvep.service.RoleService;
import com.java.wildanvep.util.ResponseUtil;

@RestController
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping()
	public SearchResponse<Role> searchRole(RoleSearchRequest request) {

		SearchResponse<Role> response = roleService.searchRole(request);
		return response;
	}

	@GetMapping("/{username}")
	public ResponseEntity<Role> getRole(@PathVariable String username) {

		Role user = roleService.getRole(username);
		return ResponseUtil.generateResponseSuccess(user);
	}

	@PostMapping()
	public ResponseEntity<Role> createRole(@RequestBody RoleRequest request, Authentication authentication) {

		Role user = roleService.createRole(request, authentication.getName());
		return ResponseUtil.generateResponseSuccess(user);
	}

	@PutMapping("/{roleCd}")
	public ResponseEntity<Role> updateRole(@RequestBody RoleRequest request, @PathVariable("roleCd") String roleCd,
			Authentication authentication) {

		request.setRoleCd(roleCd);

		Role user = roleService.updateRole(request, authentication.getName());
		return ResponseUtil.generateResponseSuccess(user);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Role> deleteRole(@PathVariable("username") String username, Authentication authentication) {

		Role user = roleService.deleteRole(username, authentication.getName());
		return ResponseUtil.generateResponseSuccess(user);
	}

}

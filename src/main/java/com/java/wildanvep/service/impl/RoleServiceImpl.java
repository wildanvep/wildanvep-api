package com.java.wildanvep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.java.wildanvep.entity.Role;
import com.java.wildanvep.exception.BadRequestException;
import com.java.wildanvep.repository.RoleRepository;
import com.java.wildanvep.request.RoleRequest;
import com.java.wildanvep.request.RoleSearchRequest;
import com.java.wildanvep.response.SearchResponse;
import com.java.wildanvep.service.RoleService;

@Service
@Repository
public class RoleServiceImpl extends CommonServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public SearchResponse<Role> searchRole(RoleSearchRequest request) {

		Page<Role> page = roleRepository.search(this.convertValueForLike(request.getRoleCd()),
				this.convertValueForLike(request.getRoleName()), this.getPageable(request));

		return this.createSearchResponse(page, request);
	}

	@Override
	public Role getRole(String roleCd) {

		Role role = roleRepository.findById(roleCd).orElse(null);

		if (role == null || role.getDeletedFlag()) {
			throw new BadRequestException("User not found");
		}

		return role;
	}

	@Override
	public Role createRole(RoleRequest request, String username) {

		Role role = new Role();
		role.setRoleCd(request.getRoleCd());
		role.setRoleName(request.getRoleName());

		this.setCreateAuditTrail(role, username);

		roleRepository.save(role);

		return role;
	}

	@Override
	public Role updateRole(RoleRequest request, String username) {

		Role role = roleRepository.findById(request.getRoleCd()).orElse(null);

		if (role == null || role.getDeletedFlag()) {
			throw new BadRequestException("Role not found");
		}

		role.setRoleName(request.getRoleName());

		this.setUpdateAuditTrail(role, username);

		roleRepository.save(role);

		return role;
	}

	@Override
	public Role deleteRole(String username, String loggedUsername) {

		Role role = roleRepository.findById(username).orElse(null);

		if (role == null || role.getDeletedFlag()) {
			throw new BadRequestException("Role not found");
		}

		this.setDeleteAuditTrail(role, loggedUsername);

		roleRepository.save(role);

		return role;
	}

}

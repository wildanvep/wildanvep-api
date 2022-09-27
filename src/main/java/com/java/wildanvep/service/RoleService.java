package com.java.wildanvep.service;

import com.java.wildanvep.entity.Role;
import com.java.wildanvep.request.RoleRequest;
import com.java.wildanvep.request.RoleSearchRequest;
import com.java.wildanvep.response.SearchResponse;

public interface RoleService extends CommonService {

	SearchResponse<Role> searchRole(RoleSearchRequest request);

	Role getRole(String roleCd);

	Role createRole(RoleRequest request, String username);

	Role updateRole(RoleRequest request, String username);

	Role deleteRole(String username, String loggedUsername);

}

package com.java.wildanvep.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleSearchRequest extends BaseSearchRequest {

	private String roleCd;
	private String roleName;

}

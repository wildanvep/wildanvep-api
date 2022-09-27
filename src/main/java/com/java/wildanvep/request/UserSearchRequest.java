package com.java.wildanvep.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest extends BaseSearchRequest {

	private String userName;
	private String fullName;
	private String email;
	private String roleCd;

}

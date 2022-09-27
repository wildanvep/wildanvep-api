package com.java.wildanvep.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String roleCd;

}

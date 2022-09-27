package com.java.wildanvep.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
	
	private String username;
	private String refreshToken;

}

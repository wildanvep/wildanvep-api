package com.java.wildanvep.service;

import com.java.wildanvep.entity.User;
import com.java.wildanvep.request.UserRequest;
import com.java.wildanvep.request.UserSearchRequest;
import com.java.wildanvep.response.SearchResponse;
import com.java.wildanvep.response.UserResponse;

public interface UserService extends CommonService {

	SearchResponse<User> searchUser(UserSearchRequest request);

	SearchResponse<UserResponse> searchUserWithNativeQuery(UserSearchRequest request);

	User getUser(String username);
	
	User createUser(UserRequest request, String username);
	
	User updateUser(UserRequest request, String username);
	
	User deleteUser(String username, String loggedUsername);

}

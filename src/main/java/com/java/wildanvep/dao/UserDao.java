package com.java.wildanvep.dao;

import java.util.List;

import com.java.wildanvep.request.UserSearchRequest;
import com.java.wildanvep.response.UserResponse;

public interface UserDao {

	List<UserResponse> searchUser(UserSearchRequest request);

	Integer countSearchUser(UserSearchRequest request);

}

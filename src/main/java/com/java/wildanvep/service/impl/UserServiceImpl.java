package com.java.wildanvep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.java.wildanvep.dao.UserDao;
import com.java.wildanvep.entity.Role;
import com.java.wildanvep.entity.User;
import com.java.wildanvep.exception.BadRequestException;
import com.java.wildanvep.repository.RoleRepository;
import com.java.wildanvep.repository.UserRepository;
import com.java.wildanvep.request.UserRequest;
import com.java.wildanvep.request.UserSearchRequest;
import com.java.wildanvep.response.SearchResponse;
import com.java.wildanvep.response.UserResponse;
import com.java.wildanvep.service.UserService;

@Service
@Repository
public class UserServiceImpl extends CommonServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public SearchResponse<User> searchUser(UserSearchRequest request) {

		Page<User> page = userRepository.search(this.convertValueForLike(request.getUserName()),
				this.convertValueForLike(request.getFullName()), this.convertValueForLike(request.getEmail()),
				this.getPageable(request));

		return this.createSearchResponse(page, request);
	}

	@Override
	public SearchResponse<UserResponse> searchUserWithNativeQuery(UserSearchRequest request) {

		List<UserResponse> resultList = userDao.searchUser(request);

		Pageable pageable = this.getPageable(request);
		final Page<UserResponse> page = new PageImpl<>(resultList, pageable, userDao.countSearchUser(request));

		return this.createSearchResponse(page, request);
	}

	@Override
	public User getUser(String username) {

		User user = userRepository.findById(username).orElse(null);

		if (user == null || user.getDeletedFlag()) {
			throw new BadRequestException("User not found");
		}

		return user;
	}

	@Override
	public User createUser(UserRequest request, String username) {

		User user = new User();

		user.setUserName(request.getUserName());
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));

		Role role = roleRepository.findById(request.getRoleCd()).orElse(null);

		if (role == null) {
			throw new BadRequestException("Role not found");
		}

		user.setRoleCd(role);

		this.setCreateAuditTrail(user, username);

		userRepository.save(user);

		return user;
	}

	@Override
	public User updateUser(UserRequest request, String username) {

		User user = userRepository.findById(request.getUserName()).orElse(null);

		if (user == null || user.getDeletedFlag()) {
			throw new BadRequestException("User not found");
		}

		user.setFullName(request.getFullName());
		user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
		user.setEmail(request.getEmail());

		Role role = roleRepository.findById(request.getRoleCd()).orElse(null);

		if (role == null) {
			throw new BadRequestException("Role not found");
		}

		user.setRoleCd(role);

		this.setUpdateAuditTrail(user, username);

		userRepository.save(user);

		return user;
	}

	@Override
	public User deleteUser(String username, String loggedUsername) {

		User user = userRepository.findById(username).orElse(null);

		if (user == null || user.getDeletedFlag()) {
			throw new BadRequestException("User not found");
		}

		this.setDeleteAuditTrail(user, loggedUsername);

		userRepository.save(user);

		return user;
	}

}

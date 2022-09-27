package com.java.wildanvep.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.wildanvep.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "from User u where u.deletedFlag = false "
			+ "and (:userName = '' or lower(u.userName) like lower(concat('%', :userName, '%'))) "
			+ "and (:fullName = '' or lower(u.fullName) like lower(concat('%', :fullName, '%'))) "
			+ "and (:email = '' or lower(u.email) like lower(concat('%', :email, '%'))) order by u.createdDt desc")
	Page<User> search(@Param("userName") String userName, @Param("fullName") String fullName,
			@Param("email") String email, Pageable pageable);

}

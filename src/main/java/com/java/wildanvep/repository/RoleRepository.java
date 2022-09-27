package com.java.wildanvep.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.wildanvep.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	@Query(value = "from Role r where r.deletedFlag = false "
			+ "and (:roleCd = '' or lower(r.roleCd) like lower(concat('%', :roleCd, '%'))) "
			+ "and (:roleName = '' or lower(r.roleName) like lower(concat('%', :roleName, '%'))) "
			+ "order by r.createdDt desc")
	Page<Role> search(@Param("roleCd") String roleCd, @Param("roleName") String roleName, Pageable pageable);

}

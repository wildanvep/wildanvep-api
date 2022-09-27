package com.java.wildanvep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_M_ROLE")
public class Role extends CommonEntity {

	@Id
	@Column(name = "ROLE_CD", nullable = false)
	private String roleCd;

	@Column(name = "ROLE_NAME", nullable = false)
	private String roleName;
	
	public Role() {
	}

	public Role(String roleCd) {

		this.roleCd = roleCd;
	}

}

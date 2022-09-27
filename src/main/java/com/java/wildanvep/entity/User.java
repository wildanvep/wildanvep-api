package com.java.wildanvep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_M_USER")
public class User extends CommonEntity {

	@Id
	@Column(name = "USERNAME", nullable = false)
	private String userName;

	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(name = "ROLE_CD", referencedColumnName = "ROLE_CD")
	private Role roleCd;

	public User() {
	}

	public User(String userName) {

		this.userName = userName;
	}

}

package com.java.wildanvep.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class CommonEntity {

	@JsonIgnore
	@Column(name = "DELETED_FLAG")
	private Boolean deletedFlag;

	@JsonIgnore
	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;

	@JsonIgnore
	@Column(name = "CREATED_DT", nullable = false)
	private Date createdDt;

	@JsonIgnore
	@Column(name = "CHANGED_BY")
	private String changedBy;

	@JsonIgnore
	@Column(name = "CHANGED_DT")
	private Date changedDt;

}

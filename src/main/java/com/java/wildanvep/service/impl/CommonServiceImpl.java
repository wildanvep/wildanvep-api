package com.java.wildanvep.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.java.wildanvep.entity.CommonEntity;
import com.java.wildanvep.request.BaseSearchRequest;
import com.java.wildanvep.response.SearchResponse;
import com.java.wildanvep.service.CommonService;

public class CommonServiceImpl implements CommonService {

	protected void setCreateAuditTrail(CommonEntity entity, String username) {
		entity.setCreatedBy(username);
		entity.setCreatedDt(new Timestamp(System.currentTimeMillis()));
		entity.setDeletedFlag(false);
	}

	protected void setUpdateAuditTrail(CommonEntity entity, String username) {
		entity.setChangedBy(username);
		entity.setChangedDt(new Timestamp(System.currentTimeMillis()));
		entity.setDeletedFlag(false);
	}

	protected void setDeleteAuditTrail(CommonEntity entity, String username) {
		entity.setDeletedFlag(true);
		entity.setChangedBy(username);
		entity.setChangedDt(new Timestamp(System.currentTimeMillis()));
	}

	protected Pageable getPageable(BaseSearchRequest request) {
		return PageRequest.of(request.getPageNo() - 1, request.getPageSize());
	}

	protected String convertValueForLike(String value) {
		return value != null ? value : StringUtils.EMPTY;
	}

	protected <T> SearchResponse<T> createSearchResponse(Page<T> page, BaseSearchRequest request) {
		List<T> data = page.toList();

		return new SearchResponse<>(request.getPageNo(), request.getPageSize(), data.size(), page.getTotalElements(),
				page.getTotalPages(), new Timestamp(System.currentTimeMillis()), data);
	}

}

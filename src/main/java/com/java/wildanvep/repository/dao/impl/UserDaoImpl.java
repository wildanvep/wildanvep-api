package com.java.wildanvep.repository.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.java.wildanvep.dao.UserDao;
import com.java.wildanvep.request.UserSearchRequest;
import com.java.wildanvep.response.UserResponse;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserResponse> searchUser(UserSearchRequest request) {

		StringBuilder sb = new StringBuilder();
		List<UserResponse> results = new ArrayList<>();

		sb.append("select ");
		sb.append("	username, ");
		sb.append("	full_name, ");
		sb.append("	email ");
		sb.append("from ");
		sb.append("	tb_m_user ");
		sb.append("where ");
		sb.append("	deleted_flag != true ");
		setSearchCriteria(sb, request);

		Query query = entityManager.createNativeQuery(sb.toString());
		query.setFirstResult((request.getPageNo() - 1) * request.getPageSize());
		query.setMaxResults(request.getPageSize());

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();

		if (rows.size() > 0) {
			for (Object[] obj : rows) {

				UserResponse vo = new UserResponse();

				vo.setUsername((String) obj[0] != null ? (String) obj[0] : null);
				vo.setFullName((String) obj[1] != null ? (String) obj[1] : null);
				vo.setEmail((String) obj[2] != null ? (String) obj[2] : null);

				results.add(vo);
			}
		}

		return results;
	}

	@Override
	public Integer countSearchUser(UserSearchRequest request) {

		StringBuilder sb = new StringBuilder();

		sb.append("select count(1) ");
		sb.append("from ");
		sb.append("	tb_m_user ");
		sb.append("where ");
		sb.append("	deleted_flag != true ");
		setSearchCriteria(sb, request);

		Query query = entityManager.createNativeQuery(sb.toString());
		query.setFirstResult((request.getPageNo() - 1) * request.getPageSize());
		query.setMaxResults(request.getPageSize());

		BigInteger result = (BigInteger) query.getSingleResult();

		return result.intValue();
	}

	private void setSearchCriteria(StringBuilder sb, UserSearchRequest request) {

		if (!StringUtils.isEmpty(request.getUserName())) {
			sb.append(" and user_name like ('%' || '" + request.getUserName() + "' || '%') ");
		}
		if (!StringUtils.isEmpty(request.getFullName())) {
			sb.append(" and full_name like ('%' || '" + request.getFullName() + "' || '%') ");
		}
		if (!StringUtils.isEmpty(request.getEmail())) {
			sb.append(" and email like ('%' || '" + request.getEmail() + "' || '%') ");
		}
	}

}

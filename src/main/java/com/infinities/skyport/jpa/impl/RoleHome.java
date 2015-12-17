package com.infinities.skyport.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.infinities.skyport.entity.Organization;
import com.infinities.skyport.entity.Role;
import com.infinities.skyport.entity.UserRole;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IRoleHome;

public class RoleHome extends AbstractJpaHome<Role> implements IRoleHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RoleHome() {
		super(Role.class);
	}

	@Override
	public List<Role> findAll(Long startId, Long endId, Integer limit,
			com.infinities.skyport.service.jpa.GenericHome.Order order) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Role> cq = cb.createQuery(Role.class);
			Root<Role> userRoot = cq.from(Role.class);
			cq.select(userRoot);
			if (order == Order.desc) {
				cq.orderBy(cb.desc(userRoot.get("id")));
			} else {
				cq.orderBy(cb.asc(userRoot.get("id")));
			}

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (startId != null) {
				Predicate pStartId = cb.le(userRoot.<Long> get("id"), startId);
				predicateList.add(pStartId);
			}

			if (endId != null) {
				Predicate pEndId = cb.ge(userRoot.<Long> get("id"), endId);
				predicateList.add(pEndId);
			}

			if (!predicateList.isEmpty()) {
				cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
			}

			final TypedQuery<Role> q = entityManager.createQuery(cq);
			if (limit != null) {
				q.setMaxResults(limit);
			}
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Role> findAllUnreferencedRolesByOrganization(Long organizationId, Long userId, Long startId, Long endId,
			Integer limit, com.infinities.skyport.service.jpa.GenericHome.Order order) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Role> cq = cb.createQuery(Role.class);
			Root<Role> roleRoot = cq.from(Role.class);
			cq.select(roleRoot);
			if (order == Order.desc) {
				cq.orderBy(cb.desc(roleRoot.get("id")));
			} else {
				cq.orderBy(cb.asc(roleRoot.get("id")));
			}

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (startId != null) {
				Predicate pStartId = cb.le(roleRoot.<Long> get("id"), startId);
				predicateList.add(pStartId);
			}

			if (endId != null) {
				Predicate pEndId = cb.ge(roleRoot.<Long> get("id"), endId);
				predicateList.add(pEndId);
			}

			if (organizationId != null) {
				Predicate pOrgId = cb.ge(roleRoot.<Organization> get("organization").<Long> get("id"), organizationId);
				predicateList.add(pOrgId);
			}

			if (userId != null) {
				Subquery<UserRole> subquery = cq.subquery(UserRole.class);
				Root<UserRole> subRootEntity = subquery.from(UserRole.class);
				subquery.select(subRootEntity);
				Predicate userPredicate = cb.equal(subRootEntity.get("user").get("id"), userId);
				Predicate rolePredicate = cb.equal(subRootEntity.get("role"), roleRoot);
				subquery.where(userPredicate, rolePredicate);
				cq.where(cb.not(cb.exists(subquery)));

			}

			if (!predicateList.isEmpty()) {
				cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
			}

			final TypedQuery<Role> q = entityManager.createQuery(cq);
			if (limit != null) {
				q.setMaxResults(limit);
			}
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
}

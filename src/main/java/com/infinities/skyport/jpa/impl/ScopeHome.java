package com.infinities.skyport.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.infinities.skyport.entity.Scope;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IScopeHome;

public class ScopeHome extends AbstractJpaHome<Scope> implements IScopeHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ScopeHome() {
		super(Scope.class);
	}

	@Override
	public List<Scope> findAll(Long startId, Long endId, Integer limit,
			com.infinities.skyport.service.jpa.GenericHome.Order order) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Scope> cq = cb.createQuery(Scope.class);
			Root<Scope> userRoot = cq.from(Scope.class);
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

			final TypedQuery<Scope> q = entityManager.createQuery(cq);
			q.setMaxResults(limit);
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

}

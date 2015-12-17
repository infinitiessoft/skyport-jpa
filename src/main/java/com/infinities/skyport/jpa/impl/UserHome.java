package com.infinities.skyport.jpa.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.infinities.skyport.entity.Organization;
import com.infinities.skyport.entity.User;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IUserHome;

public class UserHome extends AbstractJpaHome<User> implements IUserHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserHome.class);


	public UserHome() {
		super(User.class);
	}

	@Override
	public User findByLoginname(String loginname) {
		logger.debug("start getting User instance with name: {}", new Object[] { loginname });
		checkArgument(!Strings.isNullOrEmpty(loginname), "invalid loginname");
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(getEntityBeanType());
		Root<User> root = cq.from(getEntityBeanType());
		Path<?> path = root.get("loginname");
		Predicate predicate = cb.equal(path, loginname);
		cq.where(predicate);
		cq.select(root);

		TypedQuery<User> q = em.createQuery(cq);
		User user = q.getSingleResult();
		logger.debug("getting User instance with name: {}", new Object[] { loginname });
		return user;
	}

	@Override
	public List<User> findAll(Long startId, Long endId, Integer limit,
			com.infinities.skyport.service.jpa.GenericHome.Order order) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> userRoot = cq.from(User.class);
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

			final TypedQuery<User> q = entityManager.createQuery(cq);
			if (limit != null) {
				q.setMaxResults(limit);
			}
			logger.debug("get successful");
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<User> findAllByOrganization(Long organizationId, Long startId, Long endId, Integer limit,
			com.infinities.skyport.service.jpa.GenericHome.Order order) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> userRoot = cq.from(User.class);
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

			if (organizationId != null) {
				Predicate pOrgId = cb.ge(userRoot.<Organization> get("organization").<Long> get("id"), organizationId);
				predicateList.add(pOrgId);
			}

			if (!predicateList.isEmpty()) {
				cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
			}

			final TypedQuery<User> q = entityManager.createQuery(cq);
			if (limit != null) {
				q.setMaxResults(limit);
			}
			logger.debug("get successful");
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
}

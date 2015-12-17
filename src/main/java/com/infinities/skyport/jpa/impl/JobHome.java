package com.infinities.skyport.jpa.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
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
import com.infinities.skyport.entity.Job;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IJobHome;

public class JobHome extends AbstractJpaHome<Job> implements IJobHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(JobHome.class);


	public JobHome() {
		super(Job.class);
	}

	@Override
	public List<Job> findByExecutorKey(String executorKey) {
		logger.debug("start getting Jobs with executorKey: {}", new Object[] { executorKey });
		checkArgument(!Strings.isNullOrEmpty(executorKey), "invalid executorKey");
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Job> cq = cb.createQuery(getEntityBeanType());
		Root<Job> root = cq.from(getEntityBeanType());
		Path<?> path = root.get("executorKey");
		Predicate predicate = cb.equal(path, executorKey);
		cq.where(predicate);
		cq.select(root);

		TypedQuery<Job> q = em.createQuery(cq);
		List<Job> jobs = q.getResultList();
		logger.debug("getting Jobs with executorKey: {}", new Object[] { executorKey });
		return jobs;
	}

	@Override
	public List<Job> findAll(Collection<String> keys) {
		logger.debug("start getting Jobs with keys: {}", new Object[] { keys });
		checkArgument(keys != null, "invalid keys");
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Job> cq = cb.createQuery(getEntityBeanType());
		Root<Job> root = cq.from(getEntityBeanType());
		Path<String> param = root.get("id");
		Predicate predicate = param.in(keys);
		cq.where(predicate);
		cq.select(root);

		TypedQuery<Job> q = em.createQuery(cq);
		List<Job> jobs = q.getResultList();
		logger.debug("getting Jobs with keys: {}", new Object[] { keys });
		return jobs;
	}

	@Override
	public Job findByEventid(Object eventid) {
		logger.debug("start getting Jobs with eventid: {}", new Object[] { eventid });
		checkArgument(eventid != null, "invalid eventid");
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Job> cq = cb.createQuery(getEntityBeanType());
		Root<Job> root = cq.from(getEntityBeanType());
		Path<?> path = root.get("eventid");
		Predicate predicate = cb.equal(path, eventid);
		cq.where(predicate);
		cq.select(root);

		TypedQuery<Job> q = em.createQuery(cq);
		Job job = q.getSingleResult();
		logger.debug("getting Jobs with eventid: {}", new Object[] { job });
		return job;
	}
}

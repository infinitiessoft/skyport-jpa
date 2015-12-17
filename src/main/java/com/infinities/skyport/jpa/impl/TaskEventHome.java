package com.infinities.skyport.jpa.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinities.skyport.entity.TaskEvent;
import com.infinities.skyport.entity.TaskEventLog;
import com.infinities.skyport.entity.TaskEventLog.Status;
import com.infinities.skyport.entity.User;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.ITaskEventHome;

public class TaskEventHome extends AbstractJpaHome<TaskEvent> implements ITaskEventHome, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TaskEventHome.class);


	public TaskEventHome() {
		super(TaskEvent.class);
	}

	public List<TaskEvent> findAllUnfinishTask() {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TaskEvent> cq = cb.createQuery(TaskEvent.class);
		Root<TaskEvent> taskEventRoot = cq.from(TaskEvent.class);
		cq.select(taskEventRoot);

		Subquery<TaskEventLog> subquery = cq.subquery(TaskEventLog.class);
		Root<TaskEventLog> subRoot = subquery.from(TaskEventLog.class);
		subquery.select(subRoot);

		Predicate fail = cb.equal(subRoot.get("status"), Status.Fail);
		Predicate success = cb.equal(subRoot.get("status"), Status.Success);
		Predicate orPredicate = cb.or(fail, success);
		subquery.where(orPredicate);

		cq.where(cb.not(cb.exists(subquery)));
		final TypedQuery<TaskEvent> q = entityManager.createQuery(cq);
		List<TaskEvent> events = q.getResultList();
		return events;
	}

	@Override
	public List<TaskEvent> findAll(String configId, Date start, Date end, User user, Integer page, Integer perpage)
			throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<TaskEvent> cq = cb.createQuery(TaskEvent.class);
			Root<TaskEvent> taskEventRoot = cq.from(TaskEvent.class);
			cq.select(taskEventRoot);
			cq.orderBy(cb.desc(taskEventRoot.get("id")));

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (configId != null) {
				Predicate pConfig = cb.equal(taskEventRoot.get("config"), configId);
				predicateList.add(pConfig);
			}

			if (start != null) {
				Predicate pSdate = cb.greaterThanOrEqualTo(taskEventRoot.<Date> get("registerdate"), start);
				predicateList.add(pSdate);
			}

			if (end != null) {
				Predicate pEdate = cb.lessThanOrEqualTo(taskEventRoot.<Date> get("registerdate"), end);
				predicateList.add(pEdate);

			}

			if (user != null) {
				Predicate pUser = cb.equal(taskEventRoot.get("user"), user);
				predicateList.add(pUser);
			}

			if (!predicateList.isEmpty()) {
				cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
			}

			List<TaskEvent> events = new ArrayList<TaskEvent>();

			final TypedQuery<TaskEvent> q = entityManager.createQuery(cq);

			if (page != null && perpage != null) {
				int first = (page - 1) * perpage;
				int last = (page * perpage) - 1;
				q.setFirstResult(first);
				q.setMaxResults(last);
			}
			events = q.getResultList();

			logger.debug("get successful");
			return events;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<TaskEvent> findAll(String configId, Date start, Date end, User user, Long startId, Long endId,
			Integer limit, Order order) throws Exception {
		logger.debug(
				"start listing TaskEvent instance with configId:{}, start:{}, end:{}, user:{}, startId:{}, endId:{}, limit:{}, order:{}",
				new Object[] { configId, start, end, user, startId, endId, limit, order });
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<TaskEvent> cq = cb.createQuery(TaskEvent.class);
			Root<TaskEvent> taskEventRoot = cq.from(TaskEvent.class);
			cq.select(taskEventRoot);
			if (order == Order.desc) {
				cq.orderBy(cb.desc(taskEventRoot.get("id")));
			} else {
				cq.orderBy(cb.asc(taskEventRoot.get("id")));
			}

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (configId != null) {
				Predicate pConfig = cb.equal(taskEventRoot.get("config"), configId);
				predicateList.add(pConfig);
			}

			if (start != null) {
				Predicate pSdate = cb.greaterThanOrEqualTo(taskEventRoot.<Date> get("registerdate"), start);
				predicateList.add(pSdate);
			}

			if (end != null) {
				Predicate pEdate = cb.lessThanOrEqualTo(taskEventRoot.<Date> get("registerdate"), end);
				predicateList.add(pEdate);

			}

			if (user != null) {
				Predicate pUser = cb.equal(taskEventRoot.get("user"), user);
				predicateList.add(pUser);
			}

			if (startId != null) {
				Predicate pStartId = cb.le(taskEventRoot.<Long> get("id"), startId);
				predicateList.add(pStartId);
			}

			if (endId != null) {
				Predicate pEndId = cb.ge(taskEventRoot.<Long> get("id"), endId);
				predicateList.add(pEndId);
			}

			if (!predicateList.isEmpty()) {
				cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
			}

			final TypedQuery<TaskEvent> q = entityManager.createQuery(cq);
			if (limit != null) {
				q.setMaxResults(limit);
			}

			List<TaskEvent> ret = q.getResultList();

			logger.debug("listing TaskEvent instance with size:{}", new Object[] { ret.size() });
			return ret;
		} catch (Exception e) {
			throw e;
		}
	}
}

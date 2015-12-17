package com.infinities.skyport.jpa.impl;

import java.io.Serializable;

import com.infinities.skyport.entity.TaskEventLog;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.ITaskEventLogHome;

public class TaskEventLogHome extends AbstractJpaHome<TaskEventLog> implements ITaskEventLogHome, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskEventLogHome() {
		super(TaskEventLog.class);
	}

	// public String findAll(Long configId, Date start, Date end, User user,
	// Integer page, Integer perpage) {
	//
	// logger.debug("getting Log instance");
	// final EntityManager entityManager =
	// EntityManagerFactoryBuilder.getEntityManager();
	// final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	// final CriteriaQuery<Log> cq = cb.createQuery(Log.class);
	// final Root<Log> logRoot = cq.from(Log.class);
	// cq.select(logRoot);
	//
	// final List<Predicate> predicateList = Lists.newArrayList();
	//
	// if (config != null) {
	// final Predicate pConfig = cb.equal(logRoot.get("config"), config);
	// predicateList.add(pConfig);
	// }
	//
	// if (sdate != null) {
	// final Predicate pSdate = cb.greaterThanOrEqualTo(logRoot.<Timestamp>
	// get("logdate"), Timestamp.valueOf(sdate));
	// predicateList.add(pSdate);
	//
	// }
	//
	// if (edate != null) {
	// final Predicate pEdate = cb.lessThanOrEqualTo(logRoot.<Timestamp>
	// get("logdate"), Timestamp.valueOf(edate));
	// predicateList.add(pEdate);
	// }
	//
	// if (!predicateList.isEmpty()) {
	// cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
	// }
	//
	// List<Log> logList = Lists.newArrayList();
	//
	// try {
	// final TypedQuery<Log> q = entityManager.createQuery(cq);
	//
	// if (sdate == null && edate == null) {
	// q.setMaxResults(100);
	// }
	// logList = q.getResultList();
	//
	// logger.debug("get successful");
	// } catch (final RuntimeException re) {
	//
	// logger.error("get failed", re);
	// throw re;
	// } finally {
	// entityManager.close();
	// }
	//
	// // final List<Map<String, Object>> logs = Lists.newArrayList();
	//
	// // for (final Log log : logList) {
	// // logs.add(JSONUtil.getMap(log));
	// //
	// // }
	//
	// return JsonUtil.toJson(true, logList);
	//
	// }

}

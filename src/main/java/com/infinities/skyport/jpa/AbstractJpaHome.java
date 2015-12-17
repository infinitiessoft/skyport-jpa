package com.infinities.skyport.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinities.skyport.service.jpa.GenericHome;

public abstract class AbstractJpaHome<T> implements GenericHome<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AbstractJpaHome.class);
	protected transient EntityManager entityManager; // testing
	private final Class<T> entityBeanType;


	public AbstractJpaHome(Class<T> entityBeanType) {
		this.entityBeanType = entityBeanType;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// @Override
	// public void commit() {
	// if (!EntityManagerHelper.getLock()) {
	// try {
	// EntityManagerHelper.commit();
	// logger.debug("transaction is commit");
	// } catch (RuntimeException e) {
	// if (EntityManagerHelper.getEntityManager() != null &&
	// EntityManagerHelper.getEntityManager().isOpen()
	// && EntityManagerHelper.getEntityManager().getTransaction().isActive()) {
	// EntityManagerHelper.rollback();
	// logger.debug("transaction is rollback");
	// }
	// throw e;
	// } finally {
	// EntityManagerHelper.closeEntityManager();
	// logger.debug("transaction is closed");
	// }
	// }
	// }

	protected EntityManager getEntityManager() {
		if (this.entityManager != null) {
			return entityManager;
		}

		EntityManager em = EntityManagerHelper.getEntityManager();
		if (!em.getTransaction().isActive()) {
			try {
				EntityManagerHelper.beginTransaction();
			} catch (RuntimeException e) {
				EntityManagerHelper.closeEntityManager();
				logger.warn("transaction is closed unexpected", e);
			}
		}
		logger.debug("EntityManager: {} in {}", new Object[] { em.toString(), entityBeanType.getSimpleName() });

		return em;
	}

	@Override
	public void persist(T transientInstance) {
		logger.debug("start persisting {} instance", entityBeanType.getSimpleName());
		getEntityManager().persist(transientInstance);
		logger.debug("persisting {} instance", entityBeanType.getSimpleName());
	}

	@Override
	public void remove(T persistentInstance) {
		logger.debug("start removing {} instance", entityBeanType.getSimpleName());
		getEntityManager().remove(persistentInstance);
		logger.debug("removing {} instance", entityBeanType.getSimpleName());
	}

	@Override
	public T merge(T detachedInstance) {
		logger.debug("start merging {} instance", entityBeanType.getSimpleName());
		T result = getEntityManager().merge(detachedInstance);
		logger.debug("merging {} instance", entityBeanType.getSimpleName());
		return result;
	}

	@Override
	public T findById(Object id) {
		logger.debug("start getting {} instance with id: {}", new Object[] { entityBeanType.getSimpleName(), id });
		T instance = getEntityManager().find(getEntityBeanType(), id);
		logger.debug("getting {} instance with id: {}", new Object[] { entityBeanType.getSimpleName(), id });
		return instance;
	}

	@Override
	public List<T> findAll() {
		logger.debug("start listing {} instance", entityBeanType.getSimpleName());
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityBeanType());
		Root<T> taskEventRoot = cq.from(getEntityBeanType());
		cq.select(taskEventRoot);

		TypedQuery<T> q = em.createQuery(cq);
		List<T> list = q.getResultList();
		logger.debug("listing {} instance", entityBeanType.getSimpleName());
		return list;
	}

	@Override
	public void refresh(T detachedInstance) {
		logger.debug("start refresh {} instance", new Object[] { entityBeanType.getSimpleName() });
		getEntityManager().refresh(detachedInstance);
		logger.debug("refresh{} instance", new Object[] { entityBeanType.getSimpleName() });
	}

	protected Class<T> getEntityBeanType() {
		return entityBeanType;
	}

}

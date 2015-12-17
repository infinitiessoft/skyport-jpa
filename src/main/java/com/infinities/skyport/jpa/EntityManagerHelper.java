/*******************************************************************************
 * Copyright 2015 InfinitiesSoft Solutions Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.infinities.skyport.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(EntityManagerHelper.class);
	public static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
	public static ThreadLocal<EntityManagerFactory> factoryLocal = new ThreadLocal<EntityManagerFactory>();


	public static EntityManager getEntityManager() {
		EntityManager em = threadLocal.get();
		if (em == null || !em.isOpen()) {
			EntityManagerFactory factory = factoryLocal.get();
			if (factory == null || !factory.isOpen()) {
				factory = EntityManagerFactoryBuilder.getEntityManagerFactory();
				// factoryLocal.set(factory);
			}
			em = factory.createEntityManager();
			logger.debug("entitymanager: {} is created", new Object[] { em.toString() });
			threadLocal.set(em);
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		if (em != null && em.isOpen()) {
			em.close();
			logger.debug("entitymanager: {} is closed", new Object[] { em.toString() });
		} else {
			logger.debug("entitymanager is null");
		}
		threadLocal.remove();

	}

	public static void commitAndClose() {
		try {
			try {
				if (getEntityManager().getTransaction().isActive()) {
					commit();
				}
			} catch (RollbackException e) {
				logger.warn("commit fail, start rollback", e);
				if (getEntityManager().getTransaction().isActive()) {
					rollback();
				}
				throw e;
			} catch (IllegalStateException e) {
				logger.warn("transaction is not active, ignoring this exception", e);
			}
		} finally {
			closeEntityManager();
		}
	}

	// public static void closeEntityManagerFactory() {
	// emf.close();
	// }

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
		logger.debug("transaction: {} is begin", new Object[] { getEntityManager().getTransaction().toString() });
	}

	private static void rollback() {
		logger.debug("transaction exist? {},{}", new Object[] { String.valueOf(getEntityManager().getTransaction() != null),
				String.valueOf(getEntityManager().getTransaction().isActive()) });
		getEntityManager().getTransaction().rollback();
		logger.debug("transaction: {} is rollback", new Object[] { getEntityManager().getTransaction().toString() });
	}

	private static void commit() {
		logger.debug("transaction: {} is ready to commit", new Object[] { getEntityManager().getTransaction().toString() });
		getEntityManager().getTransaction().commit();
		logger.debug("transaction: {} is commit", new Object[] { getEntityManager().getTransaction().toString() });
	}

}

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

import java.util.Set;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinities.skyport.compute.entity.Disk;
import com.infinities.skyport.compute.entity.NetworkAdapter;

public abstract class AbstractTemplateBasedHome<T> extends AbstractJpaHome<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AbstractTemplateBasedHome.class);
	private static final String DELETE_DISKS_JPQL = "DELETE FROM Disk where config= :config AND instancetype= :instanceType";
	private static final String DELETE_NICS_JPQL = "DELETE FROM Nic where config= :config AND instancetype= :instanceType";


	public AbstractTemplateBasedHome(Class<T> entityBeanType) {
		super(entityBeanType);
	}

	protected void persistDisks(String configId, String instanceId, Set<Disk> disks) {
		for (Disk disk : disks) {
			disk.setInstancetype(getEntityBeanType().getSimpleName());
			disk.setInstanceid(instanceId);
			disk.setConfig(configId);
			getEntityManager().persist(disk);
		}
	}

	protected void persistNics(String configId, String instanceId, Set<NetworkAdapter> nics) {
		for (NetworkAdapter nic : nics) {
			nic.setInstancetype(getEntityBeanType().getSimpleName());
			nic.setInstanceid(instanceId);
			nic.setConfig(configId);
			getEntityManager().persist(nic);
		}
	}

	protected void deleteDisks(String configId) {
		logger.debug("removing Disk instance");
		Query query = getEntityManager().createQuery(DELETE_DISKS_JPQL);
		query = query.setParameter("config", configId);
		query = query.setParameter("instanceType", getEntityBeanType().getSimpleName());
		query.executeUpdate();
		logger.debug("removing successful");
	}

	protected void deleteNics(String configId) {
		logger.debug("removing Nic instance");
		Query query = getEntityManager().createQuery(DELETE_NICS_JPQL);
		query = query.setParameter("config", configId);
		query = query.setParameter("instanceType", getEntityBeanType().getSimpleName());
		query.executeUpdate();
		logger.debug("remove successful");
	}

}

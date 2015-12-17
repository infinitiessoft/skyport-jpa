package com.infinities.skyport.jpa.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinities.skyport.compute.entity.ResourcePool;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IResourcePoolHome;

public class ResourcePoolHome extends AbstractJpaHome<ResourcePool> implements IResourcePoolHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ResourcePoolHome.class);
	private static final String DELETE_JPQL = "DELETE FROM ResourcePool where config= :config";


	public ResourcePoolHome() {
		super(ResourcePool.class);
	}

	@Override
	public void removeAllByConfig(String configId) {
		logger.debug("removing ResourcePool instances");
		try {
			getEntityManager().createQuery(DELETE_JPQL).setParameter("config", configId).executeUpdate();
			logger.debug("remove successful");
		} catch (final RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	@Override
	public void persistAll(String configId, List<ResourcePool> objs) {
		logger.debug("persisting Vm instances");
		try {
			for (ResourcePool resourcePool : objs) {
				resourcePool.setConfig(configId);
				persist(resourcePool);
			}
			logger.debug("persist successful");
		} catch (final RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

}

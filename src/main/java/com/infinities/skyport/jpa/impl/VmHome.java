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
package com.infinities.skyport.jpa.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinities.skyport.compute.entity.Vm;
import com.infinities.skyport.jpa.AbstractTemplateBasedHome;
import com.infinities.skyport.service.jpa.IVmHome;

public class VmHome extends AbstractTemplateBasedHome<Vm> implements IVmHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(VmHome.class);
	private static final String DELETE_JPQL = "DELETE FROM Vm where config= :config";

	public VmHome() {
		super(Vm.class);
	}

	@Override
	public void removeAllByConfig(String configId) {
		logger.debug("removing Vm instances");
		try {
			this.deleteDisks(configId);
			this.deleteNics(configId);
			getEntityManager().createQuery(DELETE_JPQL).setParameter("config", configId).executeUpdate();
			logger.debug("remove successful");
		} catch (final RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	@Override
	public void persistAll(String configId, List<Vm> vms) {
		logger.debug("persisting Vm instances");
		try {
			for (Vm vm : vms) {
				this.persistDisks(configId, vm.getVmid(), vm.getDisks());
				this.persistNics(configId, vm.getVmid(), vm.getNics());
				vm.setConfig(configId);
				persist(vm);
			}
			logger.debug("persist successful");
		} catch (final RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

}

package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.compute.entity.ResourcePool;
import com.infinities.skyport.compute.entity.ResourcePool.Status;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class ResourcePoolHomeServiceImplTest extends AbstractJpaTestCase<ResourcePool> {

	private ResourcePoolHome service;
	private final String name = "name";
	private final String resourceid = "resourceid";
	private final String desc = "desc";
	// private Long config = 1L;
	private final String dataCenterId = "datacenterid";
	private final String hypervisorType = "hypervisorType";
	private final Status status = Status.Up;


	@Before
	public void setUp() throws Exception {
		service = new ResourcePoolHome();
		service.setEntityManager(em);
		mock = new ResourcePool(name, resourceid, desc, dataCenterId, hypervisorType, status);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<ResourcePool> getService() {
		return service;
	}

	@Override
	protected Class<ResourcePool> getType() {
		return ResourcePool.class;
	}

}

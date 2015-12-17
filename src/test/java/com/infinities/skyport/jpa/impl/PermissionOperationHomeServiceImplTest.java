package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.PermissionOperation;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class PermissionOperationHomeServiceImplTest extends AbstractJpaTestCase<PermissionOperation> {

	private PermissionOperationHome service;
	// private PermissionOperation mock;
	private String desc = "desc";
	private String alias = "alias";


	@Before
	public void setUp() throws Exception {
		service = new PermissionOperationHome();
		service.setEntityManager(em);
		mock = new PermissionOperation(desc, alias, null);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<PermissionOperation> getService() {
		return service;
	}

	@Override
	protected PermissionOperation getMock() {
		return mock;
	}

	@Override
	protected Class<PermissionOperation> getType() {
		return PermissionOperation.class;
	}

}

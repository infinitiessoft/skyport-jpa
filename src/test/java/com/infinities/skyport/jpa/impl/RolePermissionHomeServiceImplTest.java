package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.PermissionOperation;
import com.infinities.skyport.entity.Role;
import com.infinities.skyport.entity.RolePermission;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class RolePermissionHomeServiceImplTest extends AbstractJpaTestCase<RolePermission> {

	private RolePermissionHome service;
	private Role role = new Role();
	private PermissionOperation permissionOperation = new PermissionOperation();
	private String desc = "desc";


	@Before
	public void setUp() throws Exception {
		service = new RolePermissionHome();
		service.setEntityManager(em);
		mock = new RolePermission(role, permissionOperation, desc);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<RolePermission> getService() {
		return service;
	}

	@Override
	protected Class<RolePermission> getType() {
		return RolePermission.class;
	}

}

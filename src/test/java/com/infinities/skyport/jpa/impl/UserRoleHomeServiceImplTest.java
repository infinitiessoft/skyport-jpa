package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.Role;
import com.infinities.skyport.entity.User;
import com.infinities.skyport.entity.UserRole;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class UserRoleHomeServiceImplTest extends AbstractJpaTestCase<UserRole> {

	private UserRoleHome service;
	private Role role = new Role();
	private User user = new User();


	@Before
	public void setUp() throws Exception {
		service = new UserRoleHome();
		service.setEntityManager(em);
		mock = new UserRole(role, user);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<UserRole> getService() {
		return service;
	}

	@Override
	protected Class<UserRole> getType() {
		return UserRole.class;
	}

}

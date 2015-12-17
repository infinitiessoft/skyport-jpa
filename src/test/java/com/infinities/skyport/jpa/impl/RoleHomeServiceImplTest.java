package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.Organization;
import com.infinities.skyport.entity.Role;
import com.infinities.skyport.entity.Scope;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class RoleHomeServiceImplTest extends AbstractJpaTestCase<Role> {

	private RoleHome service;
	private String name = "name";
	private String desc = "desc";
	private Boolean enable = true;
	private Organization organization = new Organization();
	private Scope scope = new Scope();


	@Before
	public void setUp() throws Exception {
		service = new RoleHome();
		service.setEntityManager(em);
		mock = new Role(name, desc, enable, organization, scope);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<Role> getService() {
		return service;
	}

	@Override
	protected Class<Role> getType() {
		return Role.class;
	}

}

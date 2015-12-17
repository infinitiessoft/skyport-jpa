package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.Organization;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class OrganizationHomeServiceImplTest extends AbstractJpaTestCase<Organization> {

	private OrganizationHome service;
	// private Organization mock;
	private String name = "name", desc = "desc";
	private Boolean enable = true;


	@Before
	public void setUp() throws Exception {
		service = new OrganizationHome();
		service.setEntityManager(em);
		mock = new Organization(name, desc, enable, null);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<Organization> getService() {
		return service;
	}

	@Override
	protected Organization getMock() {
		return mock;
	}

	@Override
	protected Class<Organization> getType() {
		return Organization.class;
	}

}

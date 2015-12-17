package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.Scope;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class ScopeHomeServiceImplTest extends AbstractJpaTestCase<Scope> {

	private ScopeHome service;
	private String name = "name";
	private String alias = "alias";
	private String desc = "desc";


	@Before
	public void setUp() throws Exception {
		service = new ScopeHome();
		service.setEntityManager(em);
		mock = new Scope(name, alias, desc);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<Scope> getService() {
		return service;
	}

	@Override
	protected Class<Scope> getType() {
		return Scope.class;
	}

}

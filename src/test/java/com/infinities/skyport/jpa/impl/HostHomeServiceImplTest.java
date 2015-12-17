package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.compute.entity.Host;
import com.infinities.skyport.compute.entity.Statistics;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class HostHomeServiceImplTest extends AbstractJpaTestCase<Host> {

	private HostHome service;
	private String name = "name";
	private String hostid = "hostid";
	private String status = "status";
//	private Long config = 1L;
	private String desc = "desc";
	private String ip = "ip";
	private int port = 1;


	@Before
	public void setUp() throws Exception {
		service = new HostHome();
		service.setEntityManager(em);
		mock = new Host(desc, hostid, ip, name, status, port, new Statistics());
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<Host> getService() {
		return service;
	}

	@Override
	protected Host getMock() {
		return mock;
	}

	@Override
	protected Class<Host> getType() {
		return Host.class;
	}

}

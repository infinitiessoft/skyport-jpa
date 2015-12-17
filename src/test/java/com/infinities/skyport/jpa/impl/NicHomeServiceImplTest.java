package com.infinities.skyport.jpa.impl;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.compute.entity.NetworkAdapter;
import com.infinities.skyport.compute.entity.NetworkStatistics;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class NicHomeServiceImplTest extends AbstractJpaTestCase<NetworkAdapter> {

	private NicHome service;
	// private Nic mock;
	private String nicid = "nicid";
	private String mac = "mac";
	private String network = "network";
	private String name = "name";
	private String type = "type";
	// private Long config = 1L;
	private String desc = "desc";
	private int speed = 1;
	private int lineSpeed = 1;
	private String state = "state";
	private int vlanid = 1;


	@Before
	public void setUp() throws Exception {
		service = new NicHome();
		service.setEntityManager(em);
		mock =
				new NetworkAdapter(desc, mac, name, speed, lineSpeed, state, type, nicid, vlanid, network,
						new NetworkStatistics());
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<NetworkAdapter> getService() {
		return service;
	}

	@Override
	protected NetworkAdapter getMock() {
		return mock;
	}

	@Override
	protected Class<NetworkAdapter> getType() {
		return NetworkAdapter.class;
	}

}

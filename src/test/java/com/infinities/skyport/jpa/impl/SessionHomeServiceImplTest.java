package com.infinities.skyport.jpa.impl;

import java.util.Date;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.Session;
import com.infinities.skyport.entity.User;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class SessionHomeServiceImplTest extends AbstractJpaTestCase<Session> {

	private SessionHome service;
	private User user = new User();
	private String name = "name";
	private String token = "token";
	private String desc = "desc";
	private String browser = "browser";
	private Date cdate = new Date();
	private Date ddate = new Date();
	private String ip = "ip";
	private Boolean enable = true;


	@Before
	public void setUp() throws Exception {
		service = new SessionHome();
		service.setEntityManager(em);
		mock = new Session(user, name, token, desc, browser, cdate, ddate, ip, enable);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<Session> getService() {
		return service;
	}

	@Override
	protected Class<Session> getType() {
		return Session.class;
	}

}

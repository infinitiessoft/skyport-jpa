/*******************************************************************************
 * Copyright 2015 InfinitiesSoft Solutions Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
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

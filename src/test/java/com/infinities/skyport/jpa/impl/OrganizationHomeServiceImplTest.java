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

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

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

import com.infinities.skyport.entity.PermissionOperation;
import com.infinities.skyport.entity.Role;
import com.infinities.skyport.entity.RolePermission;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class RolePermissionHomeServiceImplTest extends AbstractJpaTestCase<RolePermission> {

	private RolePermissionHome service;
	private Role role = new Role();
	private PermissionOperation permissionOperation = new PermissionOperation();
	private String desc = "desc";


	@Before
	public void setUp() throws Exception {
		service = new RolePermissionHome();
		service.setEntityManager(em);
		mock = new RolePermission(role, permissionOperation, desc);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<RolePermission> getService() {
		return service;
	}

	@Override
	protected Class<RolePermission> getType() {
		return RolePermission.class;
	}

}

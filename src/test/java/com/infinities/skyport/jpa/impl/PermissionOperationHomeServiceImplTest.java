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
import com.infinities.skyport.jpa.AbstractJpaHome;

public class PermissionOperationHomeServiceImplTest extends AbstractJpaTestCase<PermissionOperation> {

	private PermissionOperationHome service;
	// private PermissionOperation mock;
	private String desc = "desc";
	private String alias = "alias";


	@Before
	public void setUp() throws Exception {
		service = new PermissionOperationHome();
		service.setEntityManager(em);
		mock = new PermissionOperation(desc, alias, null);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<PermissionOperation> getService() {
		return service;
	}

	@Override
	protected PermissionOperation getMock() {
		return mock;
	}

	@Override
	protected Class<PermissionOperation> getType() {
		return PermissionOperation.class;
	}

}

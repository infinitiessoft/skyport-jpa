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

import com.infinities.skyport.entity.TaskEvent;
import com.infinities.skyport.entity.User;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class TaskEventHomeServiceImplTest extends AbstractJpaTestCase<TaskEvent> {

	private TaskEventHome service;
	private final User user = new User();
	private final String cmd = "cmd";
	private final Date registerdate = new Date();
	private final String config = "config";
//	private final Status status = TaskEvent.Status.Initiazing;
	private final String para = "para";


	@Before
	public void setUp() throws Exception {
		service = new TaskEventHome();
		service.setEntityManager(em);
		mock = new TaskEvent(user, cmd, registerdate, config, para);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<TaskEvent> getService() {
		return service;
	}

	@Override
	protected Class<TaskEvent> getType() {
		return TaskEvent.class;
	}

}

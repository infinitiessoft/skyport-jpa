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

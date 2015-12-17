package com.infinities.skyport.jpa.impl;

import java.util.Date;

import org.junit.After;
import org.junit.Before;

import com.infinities.skyport.entity.TaskEvent;
import com.infinities.skyport.entity.TaskEventLog;
import com.infinities.skyport.entity.TaskEventLog.Status;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class TaskEventLogHomeServiceImplTest extends AbstractJpaTestCase<TaskEventLog> {

	private TaskEventLogHome service;
	private final TaskEvent taskEvent = new TaskEvent();
	private final Date logdate = new Date();
	private final Status status = TaskEventLog.Status.Initiazing;
	private final String message = "message";
	private final String detail = "detail";


	@Before
	public void setUp() throws Exception {
		service = new TaskEventLogHome();
		service.setEntityManager(em);
		mock = new TaskEventLog(taskEvent, logdate, status, message, detail);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	protected AbstractJpaHome<TaskEventLog> getService() {
		return service;
	}

	@Override
	protected Class<TaskEventLog> getType() {
		return TaskEventLog.class;
	}

}

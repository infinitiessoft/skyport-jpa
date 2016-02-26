///*******************************************************************************
// * Copyright 2015 InfinitiesSoft Solutions Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may
// * not use this file except in compliance with the License. You may obtain
// * a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations
// * under the License.
// *******************************************************************************/
//package com.infinities.skyport.jpa.impl;
//
//import org.junit.After;
//import org.junit.Before;
//
//import com.infinities.skyport.compute.entity.Host;
//import com.infinities.skyport.compute.entity.Statistics;
//import com.infinities.skyport.jpa.AbstractJpaHome;
//
//public class HostHomeServiceImplTest extends AbstractJpaTestCase<Host> {
//
//	private HostHome service;
//	private String name = "name";
//	private String hostid = "hostid";
//	private String status = "status";
////	private Long config = 1L;
//	private String desc = "desc";
//	private String ip = "ip";
//	private int port = 1;
//
//
//	@Before
//	public void setUp() throws Exception {
//		service = new HostHome();
//		service.setEntityManager(em);
//		mock = new Host(desc, hostid, ip, name, status, port, new Statistics());
//		mock.setId(id);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Override
//	protected AbstractJpaHome<Host> getService() {
//		return service;
//	}
//
//	@Override
//	protected Host getMock() {
//		return mock;
//	}
//
//	@Override
//	protected Class<Host> getType() {
//		return Host.class;
//	}
//
// }

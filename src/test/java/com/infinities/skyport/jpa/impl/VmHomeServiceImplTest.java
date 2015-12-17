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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infinities.skyport.compute.entity.Disk;
import com.infinities.skyport.compute.entity.NetworkAdapter;
import com.infinities.skyport.compute.entity.NetworkStatistics;
import com.infinities.skyport.compute.entity.Statistics;
import com.infinities.skyport.compute.entity.Vm;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.util.FormatUtil;

public class VmHomeServiceImplTest extends AbstractJpaTestCase<Vm> {

	private VmHome service;

	private String name = "name";
	private String os = "os";
	private String desc = "desc";
	private long memorysize = 1L;
	private int cpunum = 1;
//	private Long config = 1L;
//	private String creationdate = "creationdate";
	private String templateid = "templateid";
//	private String resourceid = "resourceid";
//	private String numofsockets = "numofsockets";
	private String hostclusterId = "hostclusterid";
	private String vmType = "vmtype";
	private String hypervisorType = "hypervisorType";

	private String status = "status";
//	private String uptime = "uptime";
//	private String elapsedtime = "elapsedtime";
	private String vmid = "vmid";
//	private String memusage = "memusage";
//	private String runningonhostid = "runningonhostid";
//	private String hostname = "hostname";
//	private String hypervisortype = "hypervisortype";
//	private String cpusys = "cpusys";
//	private String cpuuser = "cpuuser";
	private String runningOnHost = "host";
	private String cdIsoPath = "iso";
	private String info = "info";

	private static final String DELETE_DISKS_JPQL = "DELETE FROM Disk where config= :config AND instancetype= :instanceType";
	private static final String DELETE_NICS_JPQL = "DELETE FROM Nic where config= :config AND instancetype= :instanceType";
	private static final String DELETE_JPQL = "DELETE FROM Vm where config= :config";

	private Long id = 1L;
	private String configId = "1L";
	private Disk disk;

	private String nicid = "nicid";
	private String mac = "mac";
	private String network = "network";
	private String ip = "ip";
	private NetworkAdapter nic;

	private String diskid = "diskid";
	private String diskinterface = "diskinterface";
	private long sizegb = 1L;
	private long trueSize = 1L;
	private long apparentSize = 1L;
	private double readRate = 1D;
	private double writeRate = 1D;
	private String type = "type";
	private boolean boot = true;
	private Date creationDate = FormatUtil.getDefaultCreationDate();
	private String diskType = "disktype";
	private String internaldrivemapping = "internaldrivemapping";
	private String wipeAfterDelete = "wipeAfterDelete";
	private String propagateErrors = "propagateErrors";
	private String format = "format";

	private int speed = 1;
	private int lineSpeed = 1;
	private String state = "state";
	private int vlanid = 1;


	@Before
	public void setUp() throws Exception {
		service = new VmHome();
		service.setEntityManager(em);
		mock =
				new Vm(templateid, name, memorysize, os, cpunum, creationDate, desc, hostclusterId, vmType, hypervisorType,
						vmid, status, runningOnHost, ip, cdIsoPath, info, new Statistics());

		disk =
				new Disk(desc, diskid, sizegb, trueSize, apparentSize, readRate, writeRate, status, format, type, diskType,
						creationDate, internaldrivemapping, boot, diskinterface, wipeAfterDelete, propagateErrors, diskid,
						name, sizegb);
		disk.setId(id);

		nic =
				new NetworkAdapter(desc, mac, name, speed, lineSpeed, state, type, nicid, vlanid, network,
						new NetworkStatistics());
		nic.setId(id);

		mock.getDisks().add(disk);
		mock.getNics().add(nic);

		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemoveAllByConfig() {
		final Query diskQuery = context.mock(Query.class, "diskQuery");
		final Query nicQuery = context.mock(Query.class, "nicQuery");
		final Query vmQuery = context.mock(Query.class, "templateQuery");

		context.checking(new Expectations() {

			{
				oneOf(em).createQuery(DELETE_DISKS_JPQL);
				will(returnValue(diskQuery));
				oneOf(diskQuery).setParameter("config", configId);
				will(returnValue(diskQuery));
				oneOf(diskQuery).setParameter("instanceType", Vm.class.getSimpleName());
				will(returnValue(diskQuery));
				oneOf(diskQuery).executeUpdate();

				oneOf(em).createQuery(DELETE_NICS_JPQL);
				will(returnValue(nicQuery));
				oneOf(nicQuery).setParameter("config", configId);
				will(returnValue(nicQuery));
				oneOf(nicQuery).setParameter("instanceType", Vm.class.getSimpleName());
				will(returnValue(nicQuery));
				oneOf(nicQuery).executeUpdate();

				oneOf(em).createQuery(DELETE_JPQL);
				will(returnValue(vmQuery));
				oneOf(vmQuery).setParameter("config", configId);
				will(returnValue(vmQuery));
				oneOf(vmQuery).executeUpdate();
			}
		});

		service.removeAllByConfig(configId);

	}

	@Test
	public void testPersistAll() {
		context.checking(new Expectations() {

			{
				oneOf(em).persist(disk);
				oneOf(em).persist(nic);
				oneOf(em).persist(mock);
			}
		});

		List<Vm> vms = new ArrayList<Vm>();
		vms.add(mock);
		service.persistAll(configId, vms);

	}

	@Override
	protected AbstractJpaHome<Vm> getService() {
		return service;
	}

	@Override
	protected Class<Vm> getType() {
		return Vm.class;
	}

}

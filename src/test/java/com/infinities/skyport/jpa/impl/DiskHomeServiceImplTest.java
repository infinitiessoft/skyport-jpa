package com.infinities.skyport.jpa.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infinities.skyport.compute.entity.Disk;
import com.infinities.skyport.util.FormatUtil;

public class DiskHomeServiceImplTest {

	private DiskHome service = new DiskHome();
	private Mockery context = new JUnit4Mockery() {

		{
			setThreadingPolicy(new Synchroniser());
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};
	private EntityManager em = context.mock(EntityManager.class);

	private String diskid = "diskid";
	private String desc = "desc";
	private String diskinterface = "diskinterface";
	private long sizegb = 1L;
	private long trueSize = 1L;
	private long apparentSize = 1L;
	private double readRate = 1D;
	private double writeRate = 1D;
	private String status = "status";
	private String type = "type";
	private boolean boot = true;
	private Date creationDate = FormatUtil.getDefaultCreationDate();
	private String diskType = "disktype";
	private String internaldrivemapping = "internaldrivemapping";
	private String name = "name";
	private String wipeAfterDelete = "wipeAfterDelete";
	private String propagateErrors = "propagateErrors";
	private String format = "format";
	private Long id = 1L;

	private Disk mock = new Disk(desc, diskid, sizegb, trueSize, apparentSize, readRate, writeRate, status, format, type,
			diskType, creationDate, internaldrivemapping, boot, diskinterface, wipeAfterDelete, propagateErrors, diskid,
			name, sizegb);


	@Before
	public void setUp() throws Exception {
		mock.setId(id);
		service.setEntityManager(em);
	}

	@After
	public void tearDown() throws Exception {
		context.assertIsSatisfied();
	}

	@Test
	public void testPersist() {
		context.checking(new Expectations() {

			{
				oneOf(em).persist(mock);

			}
		});
		service.persist(mock);
	}

	@Test
	public void testRemove() {
		context.checking(new Expectations() {

			{
				oneOf(em).remove(mock);

			}
		});

		service.remove(mock);
	}

	@Test
	public void testMerge() {
		context.checking(new Expectations() {

			{
				oneOf(em).merge(mock);

			}
		});

		service.merge(mock);
	}

	@Test
	public void testFindById() {
		context.checking(new Expectations() {

			{
				oneOf(em).find(Disk.class, id);

			}
		});

		service.findById(id);
	}

	@Test
	public void testFindAll() {
		final CriteriaBuilder cb = context.mock(CriteriaBuilder.class);
		@SuppressWarnings("unchecked")
		final CriteriaQuery<Disk> cq = context.mock(CriteriaQuery.class);
		@SuppressWarnings("unchecked")
		final Root<Disk> root = context.mock(Root.class);
		@SuppressWarnings("unchecked")
		final TypedQuery<Disk> q = context.mock(TypedQuery.class);

		context.checking(new Expectations() {

			{
				oneOf(em).getCriteriaBuilder();
				will(returnValue(cb));
				oneOf(cb).createQuery(Disk.class);
				will(returnValue(cq));
				oneOf(cq).from(Disk.class);
				will(returnValue(root));
				oneOf(cq).select(root);
				oneOf(em).createQuery(cq);
				will(returnValue(q));
				oneOf(q).getResultList();

			}
		});

		service.findAll();
	}

}

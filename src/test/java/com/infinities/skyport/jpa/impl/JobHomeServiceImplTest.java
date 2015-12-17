package com.infinities.skyport.jpa.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infinities.skyport.entity.Job;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class JobHomeServiceImplTest extends AbstractJpaTestCase<Job> {

	private JobHome service;


	@Before
	public void setUp() throws Exception {
		service = new JobHome();
		service.setEntityManager(em);
		mock = new Job("id", "cmd", "args", "executorKey", "distributedKey", 1L, "uuid", 2L);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByEmptyExecutorKey() {
		service.findByExecutorKey("");
	}

	@Test
	public void testFindByExecutorKey() {
		final CriteriaBuilder cb = context.mock(CriteriaBuilder.class);
		@SuppressWarnings("unchecked")
		final CriteriaQuery<Job> cq = context.mock(CriteriaQuery.class);
		@SuppressWarnings("unchecked")
		final Root<Job> root = context.mock(Root.class);
		@SuppressWarnings("unchecked")
		final TypedQuery<Job> q = context.mock(TypedQuery.class);

		final Path<?> path = context.mock(Path.class);
		final Predicate predicate = context.mock(Predicate.class);

		final List<Job> jobs = new ArrayList<Job>();
		jobs.add(mock);

		context.checking(new Expectations() {

			{
				oneOf(em).getCriteriaBuilder();
				will(returnValue(cb));
				oneOf(cb).createQuery(Job.class);
				will(returnValue(cq));
				oneOf(cq).from(Job.class);
				will(returnValue(root));
				oneOf(cq).select(root);
				oneOf(em).createQuery(cq);
				will(returnValue(q));
				oneOf(root).get("executorKey");
				will(returnValue(path));
				oneOf(cb).equal(path, "executorKey");
				will(returnValue(predicate));
				oneOf(cq).where(predicate);
				oneOf(q).getResultList();
				will(returnValue(jobs));
			}
		});

		List<Job> rets = service.findByExecutorKey("executorKey");
		assertSame(jobs, rets);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindAllByEmptyKeys() {
		service.findAll(null);
	}

	@Test
	public void testFindAll() {
		final List<String> keys = new ArrayList<String>();
		keys.add("test1");
		keys.add("test2");

		final CriteriaBuilder cb = context.mock(CriteriaBuilder.class);
		@SuppressWarnings("unchecked")
		final CriteriaQuery<Job> cq = context.mock(CriteriaQuery.class);
		@SuppressWarnings("unchecked")
		final Root<Job> root = context.mock(Root.class);
		@SuppressWarnings("unchecked")
		final Path<String> param = context.mock(Path.class);

		@SuppressWarnings("unchecked")
		final TypedQuery<Job> q = context.mock(TypedQuery.class);

		// final Path<?> path = context.mock(Path.class);
		final Predicate predicate = context.mock(Predicate.class);

		final List<Job> jobs = new ArrayList<Job>();
		jobs.add(mock);

		context.checking(new Expectations() {

			{
				oneOf(em).getCriteriaBuilder();
				will(returnValue(cb));
				oneOf(cb).createQuery(Job.class);
				will(returnValue(cq));
				oneOf(cq).from(Job.class);
				will(returnValue(root));
				oneOf(root).get("id");
				will(returnValue(param));
				oneOf(param).in(keys);
				will(returnValue(predicate));
				oneOf(cq).where(predicate);
				will(returnValue(cq));
				oneOf(cq).select(root);
				will(returnValue(cq));
				oneOf(em).createQuery(cq);
				will(returnValue(q));
				oneOf(q).getResultList();
				will(returnValue(jobs));
			}
		});

		List<Job> ret = service.findAll(keys);
		assertSame(jobs, ret);
	}

	@Override
	protected AbstractJpaHome<Job> getService() {
		return service;
	}

	@Override
	protected Class<Job> getType() {
		return Job.class;
	}

}

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
import com.infinities.skyport.jpa.AbstractJpaHome;

public abstract class AbstractJpaTestCase<T> {

	protected EntityManager em;
	protected Mockery context;
	protected Long id = 1L;
	protected T mock;


	protected abstract AbstractJpaHome<T> getService();

	protected abstract Class<T> getType();

	protected T getMock() {
		return mock;
	}

	@Before
	public void before() throws Exception {
		context = new JUnit4Mockery() {

			{
				setThreadingPolicy(new Synchroniser());
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
		em = context.mock(EntityManager.class);
	}

	@After
	public void after() throws Exception {
		context.assertIsSatisfied();
	}

	@Test
	public void testPersist() {
		context.checking(new Expectations() {

			{
				oneOf(em).persist(getMock());

			}
		});

		getService().persist(getMock());

	}

	@Test
	public void testRemove() {
		context.checking(new Expectations() {

			{
//				oneOf(em).merge(getMock());
				oneOf(em).remove(getMock());

			}
		});

		getService().remove(getMock());
	}

	@Test
	public void testMerge() {
		context.checking(new Expectations() {

			{
				oneOf(em).merge(getMock());

			}
		});

		getService().merge(getMock());
	}

	@Test
	public void testFindById() {
		context.checking(new Expectations() {

			{
				oneOf(em).find(getType(), id);

			}
		});

		getService().findById(id);
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
				oneOf(cb).createQuery(getType());
				will(returnValue(cq));
				oneOf(cq).from(getType());
				will(returnValue(root));
				oneOf(cq).select(root);
				oneOf(em).createQuery(cq);
				will(returnValue(q));
				oneOf(q).getResultList();

			}
		});

		getService().findAll();
	}

}

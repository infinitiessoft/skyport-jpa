package com.infinities.skyport.jpa.impl;

import static org.junit.Assert.assertSame;

import java.util.Date;

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

import com.infinities.skyport.compute.entity.Disk;
import com.infinities.skyport.entity.Organization;
import com.infinities.skyport.entity.User;
import com.infinities.skyport.jpa.AbstractJpaHome;

public class UserHomeServiceImplTest extends AbstractJpaTestCase<User> {

	private UserHome service;
	private String name = "name";
	private String loginname = "loginname";
	private String password = "password";
	private String desc = "desc";
	private String salt = "salt";
	private String secret = "secret";
	private String key = "key";
	private Date lastlogin = new Date();
	private Boolean enable = true;
	private Organization organization = new Organization();


	@Before
	public void setUp() throws Exception {
		service = new UserHome();
		service.setEntityManager(em);
		mock = new User(name, loginname, password, desc, salt, secret, key, lastlogin, enable, organization);
		mock.setId(id);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByEmptyLoginname() {
		service.findByLoginname("");
	}

	@Test
	public void testFindByLoginname() {
		final CriteriaBuilder cb = context.mock(CriteriaBuilder.class);
		@SuppressWarnings("unchecked")
		final CriteriaQuery<Disk> cq = context.mock(CriteriaQuery.class);
		@SuppressWarnings("unchecked")
		final Root<Disk> root = context.mock(Root.class);
		@SuppressWarnings("unchecked")
		final TypedQuery<Disk> q = context.mock(TypedQuery.class);

		final Path<?> path = context.mock(Path.class);
		final Predicate predicate = context.mock(Predicate.class);

		context.checking(new Expectations() {

			{
				oneOf(em).getCriteriaBuilder();
				will(returnValue(cb));
				oneOf(cb).createQuery(User.class);
				will(returnValue(cq));
				oneOf(cq).from(User.class);
				will(returnValue(root));
				oneOf(cq).select(root);
				oneOf(em).createQuery(cq);
				will(returnValue(q));
				oneOf(root).get("loginname");
				will(returnValue(path));
				oneOf(cb).equal(path, loginname);
				will(returnValue(predicate));
				oneOf(cq).where(predicate);
				oneOf(q).getSingleResult();
				will(returnValue(mock));
			}
		});

		User user = service.findByLoginname(loginname);

		assertSame(mock, user);
	}

	@Override
	protected AbstractJpaHome<User> getService() {
		return service;
	}

	@Override
	protected Class<User> getType() {
		return User.class;
	}

}

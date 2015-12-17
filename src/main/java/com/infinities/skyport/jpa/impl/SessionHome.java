package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.entity.Session;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.ISessionHome;

public class SessionHome extends AbstractJpaHome<Session> implements ISessionHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionHome() {
		super(Session.class);
	}

}

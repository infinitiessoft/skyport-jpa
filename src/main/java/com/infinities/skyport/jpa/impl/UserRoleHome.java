package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.entity.UserRole;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IUserRoleHome;

public class UserRoleHome extends AbstractJpaHome<UserRole> implements IUserRoleHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRoleHome() {
		super(UserRole.class);
	}

}

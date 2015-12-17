package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.entity.RolePermission;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IRolePermissionHome;

public class RolePermissionHome extends AbstractJpaHome<RolePermission> implements IRolePermissionHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RolePermissionHome() {
		super(RolePermission.class);
	}

}

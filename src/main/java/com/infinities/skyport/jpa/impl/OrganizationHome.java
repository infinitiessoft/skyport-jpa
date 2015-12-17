package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.entity.Organization;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IOrganizationHome;

public class OrganizationHome extends AbstractJpaHome<Organization> implements IOrganizationHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrganizationHome() {
		super(Organization.class);
	}

}

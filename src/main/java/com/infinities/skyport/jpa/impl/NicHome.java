package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.compute.entity.NetworkAdapter;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.INicHome;

public class NicHome extends AbstractJpaHome<NetworkAdapter> implements INicHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NicHome() {
		super(NetworkAdapter.class);
	}

}

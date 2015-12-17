package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.compute.entity.Host;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IHostHome;

public class HostHome extends AbstractJpaHome<Host> implements IHostHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HostHome() {
		super(Host.class);
	}

}

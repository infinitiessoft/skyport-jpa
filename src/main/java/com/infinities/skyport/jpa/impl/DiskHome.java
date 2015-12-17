package com.infinities.skyport.jpa.impl;

import com.infinities.skyport.compute.entity.Disk;
import com.infinities.skyport.jpa.AbstractJpaHome;
import com.infinities.skyport.service.jpa.IDiskHome;

public class DiskHome extends AbstractJpaHome<Disk> implements IDiskHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiskHome() {
		super(Disk.class);
	}

}

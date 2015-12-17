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

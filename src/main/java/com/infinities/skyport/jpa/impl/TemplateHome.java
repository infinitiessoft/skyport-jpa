package com.infinities.skyport.jpa.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinities.skyport.compute.entity.Template;
import com.infinities.skyport.jpa.AbstractTemplateBasedHome;
import com.infinities.skyport.service.jpa.ITemplateHome;

public class TemplateHome extends AbstractTemplateBasedHome<Template> implements ITemplateHome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TemplateHome.class);
	private static final String DELETE_JPQL = "DELETE FROM TEMPLATE where config= :config";


	public TemplateHome() {
		super(Template.class);
	}

	@Override
	public void removeAllByConfig(String configId) {
		logger.debug("removing Template instances");
		try {
			this.deleteDisks(configId);
			this.deleteNics(configId);
			Query query = getEntityManager().createQuery(DELETE_JPQL);
			query = query.setParameter("config", configId);
			query.executeUpdate();
			logger.debug("remove successful");
		} catch (final RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	@Override
	public void persistAll(String configId, List<Template> templates) {
		logger.debug("persisting Template instances");
		try {
			for (Template template : templates) {
				this.persistDisks(configId, template.getTemplateid(), template.getDisks());
				this.persistNics(configId, template.getTemplateid(), template.getNics());
				template.setConfig(configId);
				persist(template);
			}
			logger.debug("persist successful");
		} catch (final RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}

	}

}

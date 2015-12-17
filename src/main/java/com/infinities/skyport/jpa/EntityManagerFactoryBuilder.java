package com.infinities.skyport.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.infinities.skyport.util.PropertiesHolder;

//build entitymanagerfactory
public class EntityManagerFactoryBuilder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(EntityManagerFactoryBuilder.class);
	private static final String PERSISTENCE_UNIT_NAME = JpaProperties.PERSISTENCE_UNIT_NAME;
	private static final String JPA_PROPERTIES_FILE = JpaProperties.JPA_PROPERTIES_FILE;
	public static EntityManagerFactory emf;

	static {
		String configFileLocation = PropertiesHolder.CONFIG_FOLDER + File.separator + JPA_PROPERTIES_FILE;
		Properties prop = new Properties();
		if (!Strings.isNullOrEmpty(JPA_PROPERTIES_FILE)) {
			try {
				logger.debug("Load ConfigFile: {}", configFileLocation);
				prop.load(new FileInputStream(configFileLocation));
			} catch (FileNotFoundException e) {
				logger.warn("File " + configFileLocation + " not found", e);
			} catch (IOException e) {
				logger.warn("Loading File " + configFileLocation + " fail", e);
			}
		}

		if (prop.isEmpty()) {
			logger.debug("Properites is empty, Create EntityManagerFactory {}", PERSISTENCE_UNIT_NAME);
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		} else {
			for (Object key : prop.keySet()) {
				logger.debug("jpa property key: {}, value: {}", new Object[] { key, prop.get(key) });
			}
			logger.debug("Properites is found, Create EntityManagerFactory {}", PERSISTENCE_UNIT_NAME);
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, prop);
		}
	}


	private EntityManagerFactoryBuilder() {

	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static synchronized void shutdown() {
		logger.trace("entityManagerFactory status: {}", getEntityManagerFactory().isOpen());
		if (getEntityManagerFactory().isOpen()) {
			getEntityManagerFactory().close();
			EntityManagerHelper.factoryLocal.remove();
		}
	}

}

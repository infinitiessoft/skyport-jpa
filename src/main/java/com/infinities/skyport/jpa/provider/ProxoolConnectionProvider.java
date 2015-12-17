package com.infinities.skyport.jpa.provider;

/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2007, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors. �All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.Stoppable;

/**
 * A connection provider that uses a Proxool connection pool. Hibernate will use
 * this by default if the <tt>hibernate.proxool.*</tt> properties are set.
 * 
 * @see ConnectionProvider
 */
public class ProxoolConnectionProvider implements ConnectionProvider, Configurable, Stoppable, ServiceRegistryAwareService {

	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {

		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {

		return null;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {

	}

	@Override
	public void stop() {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void configure(Map configurationValues) {

	}

	@Override
	public Connection getConnection() throws SQLException {

		return null;
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {

	}

	@Override
	public boolean supportsAggressiveRelease() {

		return false;
	}

	// public static final ProxoolMessageLogger LOG = Logger.getMessageLogger(
	// ProxoolMessageLogger.class,
	// ProxoolConnectionProvider.class.getName());
	//
	// private static final String PROXOOL_JDBC_STEM = "proxool.";
	//
	// private String proxoolAlias;
	//
	// // TRUE if the pool is borrowed from the outside, FALSE if we used to
	// create
	// // it
	// private boolean existingPool;
	//
	// // Not null if the Isolation level has been specified in the
	// configuration
	// // file.
	// // Otherwise, it is left to the Driver's default value.
	// private Integer isolation;
	//
	// private boolean autocommit;
	//
	// private ServiceRegistryImplementor serviceRegistry;
	//
	// private final Object lock = new Object();
	//
	// // private final ReentrantLock lock = new ReentrantLock();
	//
	// // private BlockingQueue taskQueue = new BlockingQueue();
	//
	// // org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider
	// /**
	// * Grab a connection
	// *
	// * @return a JDBC connection
	// * @throws SQLException
	// */
	// // public Connection getConnection() throws SQLException {
	// // // get a connection from the pool (thru DriverManager, cfr. Proxool
	// doc)
	// // synchronized (queue) {
	// // queue.addLast(Thread.currentThread());
	// // queue.notify();
	// //
	// // Connection c = null;
	// // do {
	// //
	// // try {
	// // c = DriverManager.getConnection(proxoolAlias);
	// // } catch (java.sql.SQLException e) {
	// // // e.printStackTrace();
	// // String message = e.getMessage();
	// //
	// // if (message.toLowerCase().contains(
	// // "checksimultaneousbuildthrottle")
	// // || message.toLowerCase().contains("quickrefuse")
	// // || message.toLowerCase().contains(
	// // "maximum connection")) {
	// // try {
	// // synchronized (this) {
	// // this.wait(1000);
	// // }
	// // } catch (InterruptedException ie) {
	// // }
	// // } else {
	// // throw e;
	// // }
	// //
	// // }
	// //
	// // } while (c == null);
	// //
	// // // while (c == null) {
	// // // try {
	// // // c = DriverManager.getConnection(proxoolAlias);
	// // // } catch (java.sql.SQLException e) {
	// // // // e.printStackTrace();
	// // // String message = e.getMessage();
	// // //
	// // // if (message.toLowerCase().contains(
	// // // "checksimultaneousbuildthrottle")
	// // // || message.toLowerCase().contains("quickrefuse")
	// // // || message.toLowerCase().contains("maximum connection")) {
	// // // try {
	// // // Thread.currentThread().sleep(500);
	// // // } catch (InterruptedException ie) {
	// // // }
	// // // } else {
	// // // throw e;
	// // // }
	// // //
	// // // }
	// // // }
	// //
	// // // set the Transaction Isolation if defined
	// // if (isolation != null)
	// // c.setTransactionIsolation(isolation.intValue());
	// //
	// // // toggle autoCommit to false if set
	// // if (c.getAutoCommit() != autocommit)
	// // c.setAutoCommit(autocommit);
	// //
	// // // return the connection
	// // return c;
	// // }
	// // }
	//
	// public Connection getConnection() throws SQLException {
	// // get a connection from the pool (thru DriverManager, cfr. Proxool doc)
	//
	// Connection c = null;
	//
	// try {
	// synchronized(lock){
	// c = DriverManager.getConnection(proxoolAlias);
	// }
	// if(c!=null){
	// if (isolation != null)
	// c.setTransactionIsolation(isolation.intValue());
	//
	// // toggle autoCommit to false if set
	// if (c.getAutoCommit() != autocommit)
	// c.setAutoCommit(autocommit);
	//
	// // return the connection
	// return c;
	// }else{
	// synchronized(lock){
	// return getConnectionWait();
	// }
	// }
	// } catch (java.sql.SQLException e) {
	// // e.printStackTrace();
	// String message = e.getMessage();
	//
	// if (message.toLowerCase().contains(
	// "checksimultaneousbuildthrottle")
	// || message.toLowerCase().contains("quickrefuse")
	// || message.toLowerCase().contains(
	// "maximum connection")) {
	// synchronized(lock){
	// return getConnectionWait();
	// }
	// } else {
	// throw e;
	// }
	//
	// }
	//
	//
	// // while(true) {
	// //
	// // try {
	// // c = DriverManager.getConnection(proxoolAlias);
	// // if(c!=null){
	// // if (isolation != null)
	// // c.setTransactionIsolation(isolation.intValue());
	// //
	// // // toggle autoCommit to false if set
	// // if (c.getAutoCommit() != autocommit)
	// // c.setAutoCommit(autocommit);
	// //
	// // // return the connection
	// // return c;
	// // }
	// // } catch (java.sql.SQLException e) {
	// // // e.printStackTrace();
	// // String message = e.getMessage();
	// //
	// // if (message.toLowerCase().contains(
	// // "checksimultaneousbuildthrottle")
	// // || message.toLowerCase().contains("quickrefuse")
	// // || message.toLowerCase().contains(
	// // "maximum connection")) {
	// // try {
	// // synchronized (this) {
	// // this.wait(1000);
	// // }
	// // } catch (InterruptedException ie) {
	// // }
	// // } else {
	// // throw e;
	// // }
	// //
	// // }
	// //
	// // }
	//
	// }
	//
	// private final Connection getConnectionWait() throws SQLException{
	// try { Thread.sleep(250); } catch (InterruptedException e){}
	// Connection c=null;
	//
	// try {
	// synchronized(lock){
	// c = DriverManager.getConnection(proxoolAlias);
	// }
	// if(c!=null){
	// if (isolation != null)
	// c.setTransactionIsolation(isolation.intValue());
	//
	// // toggle autoCommit to false if set
	// if (c.getAutoCommit() != autocommit)
	// c.setAutoCommit(autocommit);
	//
	// // return the connection
	// return c;
	// }else{
	// return getConnectionWait();
	// }
	//
	// } catch (java.sql.SQLException e) {
	// // e.printStackTrace();
	// String message = e.getMessage();
	//
	// if (message.toLowerCase().contains(
	// "checksimultaneousbuildthrottle")
	// || message.toLowerCase().contains("quickrefuse")
	// || message.toLowerCase().contains(
	// "maximum connection")) {
	// synchronized(lock){
	// return getConnectionWait();
	// }
	// }else{
	// throw e;
	// }
	//
	// }
	// }
	//
	// @Override
	// public boolean isUnwrappableAs(Class unwrapType) {
	// return ConnectionProvider.class.equals(unwrapType)
	// || ProxoolConnectionProvider.class.isAssignableFrom(unwrapType);
	// }
	//
	// @Override
	// @SuppressWarnings({ "unchecked" })
	// public <T> T unwrap(Class<T> unwrapType) {
	// if (ConnectionProvider.class.equals(unwrapType)
	// || ProxoolConnectionProvider.class.isAssignableFrom(unwrapType)) {
	// return (T) this;
	// } else {
	// throw new UnknownUnwrapTypeException(unwrapType);
	// }
	// }
	//
	// /**
	// * Dispose of a used connection.
	// *
	// * @param conn
	// * a JDBC connection
	// * @throws SQLException
	// */
	// public void closeConnection(Connection conn) throws SQLException {
	// conn.close();
	//
	// try {
	// this.notifyAll();
	// } catch (Exception e) {
	//
	// }
	// }
	//
	// /**
	// * Initialize the connection provider from given properties.
	// *
	// * @param props
	// * <tt>SessionFactory</tt> properties
	// */
	// public void configure(Map props) throws HibernateException {
	//
	// // Get the configurator files (if available)
	// String jaxpFile = (String) props.get(Environment.PROXOOL_XML);
	// String propFile = (String) props.get(Environment.PROXOOL_PROPERTIES);
	// String externalConfig = (String) props
	// .get(Environment.PROXOOL_EXISTING_POOL);
	//
	// // Default the Proxool alias setting
	// proxoolAlias = (String) props.get(Environment.PROXOOL_POOL_ALIAS);
	//
	// Properties connectionProps = ConnectionProviderInitiator
	// .getConnectionProperties(props);
	//
	// LOG.connectionProperties(ConfigurationHelper.maskOut(connectionProps,
	// "password"));
	//
	// // Configured outside of Hibernate (i.e. Servlet container, or Java Bean
	// // Container
	// // already has Proxool pools running, and this provider is to just
	// // borrow one of these
	// if ("true".equals(externalConfig)) {
	// // Validate that an alias name was provided to determine which pool
	// // to use
	// if (!StringHelper.isNotEmpty(proxoolAlias)) {
	// String msg = LOG
	// .unableToConfigureProxoolProviderToUseExistingInMemoryPool(Environment.PROXOOL_POOL_ALIAS);
	// LOG.error(msg);
	// throw new HibernateException(msg);
	// }
	// // Append the stem to the proxool pool alias
	// proxoolAlias = PROXOOL_JDBC_STEM + proxoolAlias;
	//
	// // Set the existing pool flag to true
	// existingPool = true;
	//
	// LOG.configuringProxoolProviderUsingExistingPool(proxoolAlias);
	//
	// // Configured using the JAXP Configurator
	// } else if (StringHelper.isNotEmpty(jaxpFile)) {
	//
	// LOG.configuringProxoolProviderUsingJaxpConfigurator(jaxpFile);
	//
	// // Validate that an alias name was provided to determine which pool
	// // to use
	// if (!StringHelper.isNotEmpty(proxoolAlias)) {
	// String msg = LOG
	// .unableToConfigureProxoolProviderToUseJaxp(Environment.PROXOOL_POOL_ALIAS);
	// LOG.error(msg);
	// throw new HibernateException(msg);
	// }
	//
	// try {
	// JAXPConfigurator.configure(jaxpFile, false);
	// // JAXPConfigurator.configure(
	// // ConfigHelper.getConfigStreamReader( jaxpFile ), false );
	// } catch (ProxoolException e) {
	// String msg = LOG.unableToLoadJaxpConfiguratorFile(jaxpFile);
	// LOG.error(msg, e);
	// throw new HibernateException(msg, e);
	// }
	//
	// // Append the stem to the proxool pool alias
	// proxoolAlias = PROXOOL_JDBC_STEM + proxoolAlias;
	// LOG.configuringProxoolProviderToUsePoolAlias(proxoolAlias);
	//
	// // Configured using the Properties File Configurator
	// } else if (StringHelper.isNotEmpty(propFile)) {
	//
	// LOG.configuringProxoolProviderUsingPropertiesFile(propFile);
	//
	// // Validate that an alias name was provided to determine which pool
	// // to use
	// if (!StringHelper.isNotEmpty(proxoolAlias)) {
	// String msg = LOG
	// .unableToConfigureProxoolProviderToUsePropertiesFile(Environment.PROXOOL_POOL_ALIAS);
	// LOG.error(msg);
	// throw new HibernateException(msg);
	// }
	//
	// try {
	// PropertyConfigurator.configure(ConfigHelper
	// .getConfigProperties(propFile));
	// } catch (ProxoolException e) {
	// String msg = LOG.unableToLoadPropertyConfiguratorFile(propFile);
	// LOG.error(msg, e);
	// throw new HibernateException(msg, e);
	// }
	//
	// // Append the stem to the proxool pool alias
	// proxoolAlias = PROXOOL_JDBC_STEM + proxoolAlias;
	// LOG.configuringProxoolProviderToUsePoolAlias(proxoolAlias);
	// }
	//
	// // Remember Isolation level
	// isolation = ConfigurationHelper
	// .getInteger(Environment.ISOLATION, props);
	// if (isolation != null)
	// LOG.jdbcIsolationLevel(Environment.isolationLevelToString(isolation
	// .intValue()));
	//
	// autocommit = ConfigurationHelper.getBoolean(Environment.AUTOCOMMIT,
	// props);
	// LOG.autoCommmitMode(autocommit);
	// }
	//
	// /**
	// * Release all resources held by this provider. JavaDoc requires a second
	// * sentence.
	// *
	// * @throws HibernateException
	// */
	// public void close() throws HibernateException {
	// // If the provider was leeching off an existing pool don't close it
	// if (existingPool) {
	// return;
	// }
	//
	// // We have created the pool ourselves, so shut it down
	// try {
	// if (ProxoolFacade.getAliases().length == 1) {
	// ProxoolFacade.shutdown(0);
	// } else {
	// ProxoolFacade.removeConnectionPool(proxoolAlias
	// .substring(PROXOOL_JDBC_STEM.length()));
	// }
	// } catch (Exception e) {
	// // If you're closing down the ConnectionProvider chances are an
	// // is not a real big deal, just warn
	// String msg = LOG.exceptionClosingProxoolPool();
	// LOG.warn(msg, e);
	// throw new HibernateException(msg, e);
	// }
	// }
	//
	// /**
	// * @see ConnectionProvider#supportsAggressiveRelease()
	// */
	// public boolean supportsAggressiveRelease() {
	// return false;
	// }
	//
	// @Override
	// public void injectServices(ServiceRegistryImplementor serviceRegistry) {
	//
	// this.serviceRegistry = serviceRegistry;
	// }
	//
	// @Override
	// public void stop() {
	//
	// close();
	// }

}

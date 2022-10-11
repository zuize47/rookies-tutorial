package com.nashtech.rookies.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

public class JpaEntityManagerFactory {

	private final Class<?>[] entityClasses;

	public JpaEntityManagerFactory(Class<?>[] entityClasses) {
		this.entityClasses = entityClasses;
	}

	public EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	public void close () {
//		getEntityManagerFactory().close();
	}

	protected EntityManagerFactory getEntityManagerFactory() {
		PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(getClass().getSimpleName());
		Map<String, Object> configuration = new HashMap<>();
		return new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(persistenceUnitInfo),
				configuration).build();
	}

	protected PersistenceUnitInfoImpl getPersistenceUnitInfo(String name) {
		return new PersistenceUnitInfoImpl(name, getEntityClassNames(), getProperties());
	}

	protected List<String> getEntityClassNames() {
		return Arrays.asList(getEntities()).stream().map(Class::getName).collect(Collectors.toList());
	}

	protected Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.use_sql_comments", "true");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.connection.datasource", DataSourceUtils.getDataSource());
		return properties;
	}

	protected Class<?>[] getEntities() {
		return entityClasses;
	}

	static class PersistenceUnitInfoImpl implements PersistenceUnitInfo {

		public static final String JPA_VERSION = "2.1";

		private final String persistenceUnitName;

		private PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;

		private final List<String> managedClassNames;

		private final List<String> mappingFileNames = new ArrayList<>();

		private final Properties properties;

		private DataSource jtaDataSource;

		private DataSource nonJtaDataSource;

		public PersistenceUnitInfoImpl(String persistenceUnitName, List<String> managedClassNames,
				Properties properties) {
			this.persistenceUnitName = persistenceUnitName;
			this.managedClassNames = managedClassNames;
			this.properties = properties;
		}

		@Override
		public String getPersistenceUnitName() {
			return persistenceUnitName;
		}

		@Override
		public String getPersistenceProviderClassName() {
			return HibernatePersistenceProvider.class.getName();
		}

		@Override
		public PersistenceUnitTransactionType getTransactionType() {
			return transactionType;
		}

		@Override
		public DataSource getJtaDataSource() {
			return jtaDataSource;
		}

		public PersistenceUnitInfoImpl setJtaDataSource(DataSource jtaDataSource) {
			this.jtaDataSource = jtaDataSource;
			this.nonJtaDataSource = null;
			transactionType = PersistenceUnitTransactionType.JTA;
			return this;
		}

		@Override
		public DataSource getNonJtaDataSource() {
			return nonJtaDataSource;
		}

		public PersistenceUnitInfoImpl setNonJtaDataSource(DataSource nonJtaDataSource) {
			this.nonJtaDataSource = nonJtaDataSource;
			this.jtaDataSource = null;
			transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
			return this;
		}

		@Override
		public List<String> getMappingFileNames() {
			return mappingFileNames;
		}

		@Override
		public List<URL> getJarFileUrls() {
			return Collections.emptyList();
		}

		@Override
		public URL getPersistenceUnitRootUrl() {
			return null;
		}

		@Override
		public List<String> getManagedClassNames() {
			return managedClassNames;
		}

		@Override
		public boolean excludeUnlistedClasses() {
			return false;
		}

		@Override
		public SharedCacheMode getSharedCacheMode() {
			return SharedCacheMode.UNSPECIFIED;
		}

		@Override
		public ValidationMode getValidationMode() {
			return ValidationMode.AUTO;
		}

		public Properties getProperties() {
			return properties;
		}

		@Override
		public String getPersistenceXMLSchemaVersion() {
			return JPA_VERSION;
		}

		@Override
		public ClassLoader getClassLoader() {
			return Thread.currentThread().getContextClassLoader();
		}

		@Override
		public void addTransformer(ClassTransformer transformer) {

		}

		@Override
		public ClassLoader getNewTempClassLoader() {
			return null;
		}
	}
}

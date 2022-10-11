package com.nashtech.rookies.utils;

import java.util.Properties;
import java.util.function.Function;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static DataSource dataSource;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}

		dataSource = DataSourceUtils.getDataSource();

		var prop = new Properties();
		prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.use_sql_comments", "true");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.format_sql", "true");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		

		Configuration cfg = new Configuration();
		cfg.setProperties(prop);

		registry = new StandardServiceRegistryBuilder().applySetting(Environment.DATASOURCE, dataSource)
				
				.applySettings(cfg.getProperties()).build();

		MetadataSources sources = new MetadataSources(registry)
				.addAnnotatedClass(com.nashtech.rookies.model.Author.class)
				.addAnnotatedClass(com.nashtech.rookies.model.Book.class);
		Metadata metadata = sources.getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();

		return sessionFactory;
	}

	public static <T> T doInSession(Function<Session, T> consumer) {
		try (var session = getSessionFactory().openSession()) {
			session.beginTransaction();
			T t = consumer.apply(session);
			session.getTransaction().commit();
			return t;
		}
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}

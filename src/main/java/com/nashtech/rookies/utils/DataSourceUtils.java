package com.nashtech.rookies.utils;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtils {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	private DataSourceUtils() {
	}

	public static DataSource getDataSource() {
		if (ds != null) {
			return ds.getDataSource();
		}
		config.setJdbcUrl("jdbc:h2:mem:bookstore");
		config.setUsername("sa");
		config.setPassword("");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
		return ds;

	}
}

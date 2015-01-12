package com.rainty.sample.starter.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * A sample Dbconfiguration for H2 embedded database. It's for demo and test
 * purpose. Not for production. For Production purpose, please use
 * OracleDbconfig
 * 
 * @author Lance
 *
 */
@Configuration
public class H2DatabaseConfig {

	/**
	 * A sample datasource for pooled embedded H2 Database. The datasource is for
	 * test and demo purpose only.
	 *
	 * @return Datasource
	 */
	@Bean(destroyMethod = "shutdown")
	public EmbeddedDatabase h2DataSource() {
		final EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("initial_db.sql")
				.build();
		return database;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {

		final EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		vendorAdapter.setShowSql(true);

		final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(h2DataSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

}

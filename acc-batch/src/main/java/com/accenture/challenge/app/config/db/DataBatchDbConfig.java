package com.accenture.challenge.app.config.db;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "dataBatchEntityManagerFactory",
		transactionManagerRef = "dataBatchTransactionManager",
		basePackages = { "com.accenture.challenge.sales.dao" }
		)
public class DataBatchDbConfig {

	@Autowired
	Environment enviroment;

	@Bean(name = "dataBatchDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(enviroment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(enviroment.getProperty("spring.datasource.url"));
		dataSource.setUsername(enviroment.getProperty("spring.datasource.username"));
		dataSource.setPassword(enviroment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Primary
	@Bean(name = "dataBatchEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean 
		dataBatchEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("dataBatchDataSource") DataSource dataSource
			) {
		return
				builder
				.dataSource(dataSource)
				.packages("com.accenture.challenge.sales.dao")
				.persistenceUnit("dataBatch")
				.build();
	}
	
	@Primary
	@Bean(name = "dataBatchTransactionManager")
	public PlatformTransactionManager dataBatchTransactionManager(
			@Qualifier("dataBatchEntityManagerFactory") EntityManagerFactory
			dataBatchEntityManagerFactory
			) {
		return new JpaTransactionManager(dataBatchEntityManagerFactory);
	}
}


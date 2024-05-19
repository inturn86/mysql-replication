package com.inturn.mysqlreplication.config;

import com.inturn.mysqlreplication.config.vo.DatabaseConsts;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class DataSourceConfiguration {

	@Bean(DatabaseConsts.MASTER_SERVER)
	@ConfigurationProperties(prefix = "spring.datasource.master.hikari")
	public DataSource masterDataSource() {
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.build();
	}

	@Bean(DatabaseConsts.SLAVE_SERVER)
	@ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.build();
	}

	@Bean
	public DataSource routingDataSource(
			@Qualifier(DatabaseConsts.MASTER_SERVER) DataSource masterDataSource,
			@Qualifier(DatabaseConsts.SLAVE_SERVER) DataSource slaveDataSource
	) {
		RoutingDataSource routingDataSource = new RoutingDataSource();

		HashMap<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DatabaseConsts.MASTER_SERVER, masterDataSource);
		dataSourceMap.put(DatabaseConsts.SLAVE_SERVER, slaveDataSource);

		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(masterDataSource);

		return routingDataSource;
	}

	@Bean
	@Primary
	public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
		DataSource determinedDataSource = routingDataSource(masterDataSource(), slaveDataSource());
		return new LazyConnectionDataSourceProxy(determinedDataSource);
	}
}

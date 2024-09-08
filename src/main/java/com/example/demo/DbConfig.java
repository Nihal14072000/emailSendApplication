package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
	@SuppressWarnings("rawtypes")
	@Bean
    public DataSource dataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://dataproject-db.c1mk04g60jm7.ap-southeast-2.rds.amazonaws.com:5432/postgres");
        dataSourceBuilder.username("projectdb");
        dataSourceBuilder.password("Nihal1407");
        return dataSourceBuilder.build();
    }
}

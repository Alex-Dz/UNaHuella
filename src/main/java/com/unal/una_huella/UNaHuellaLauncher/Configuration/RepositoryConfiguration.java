package com.unal.una_huella.UNaHuellaLauncher.Configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.unal.una_huella.UNaHuellaLauncher.Entities"})
@EnableJpaRepositories(basePackages = {"com.unal.una_huella.UNaHuellaLauncher.Repository"})
@EnableTransactionManagement

public class RepositoryConfiguration {
    
}

package com.unal.una_huella.UNaHuellaLauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.unal.una_huella.UNaHuellaLauncher.Entities")
@EnableJpaRepositories("com.unal.una_huella.UNaHuellaLauncher.Repositories")
public class UNaHuellaLauncherApplication {

    public static void main(String[] args) {
        SpringApplication.run(UNaHuellaLauncherApplication.class, args);
    }

}

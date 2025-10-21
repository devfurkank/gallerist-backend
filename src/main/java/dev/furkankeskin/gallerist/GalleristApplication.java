package dev.furkankeskin.gallerist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"dev.furkankeskin"})
@EnableJpaRepositories(basePackages = {"dev.furkankeskin"})
@SpringBootApplication
public class GalleristApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleristApplication.class, args);
    }

}

package com.gabriel.helpdesk.config;

import com.gabriel.helpdesk.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DataBaseService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean dbInit() {
        if (value.equals("create")) {
            this.dbService.dbInit();
        }

        return false;
    }

}

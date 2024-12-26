package com.domain.springframework;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "EMS API", version = "1.0.0", description = "Employee Management System API"))
public class EMSAPI {
    /**
     * Start the Spring Boot application
     * @param args shell arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(EMSAPI.class, args);
    }
}

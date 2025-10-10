package com.shreeeyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ShreeeyanApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShreeeyanApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShreeeyanApplication.class);
    }
}

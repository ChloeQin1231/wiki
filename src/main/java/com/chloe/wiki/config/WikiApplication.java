package com.chloe.wiki.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.chloe")
@SpringBootApplication
public class WikiApplication {
    private static final Logger logger = LoggerFactory.getLogger(WikiApplication.class);
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(WikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info("address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}

package com.parser.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.parser.service", "com.parser.mapper"})
public class AppConfig {
}

package com.tip.alumnos;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext ac) {
        TestPropertyValues.of(
                "spring.datasource.url=jdbc:postgresql://localhost/alumnostip",
                "spring.datasource.username=alumnos",
                "spring.datasource.password=tip"
        ).applyTo(ac.getEnvironment());
    }
}
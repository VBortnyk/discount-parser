package com.parser;

import com.parser.configuration.AppConfig;
import com.parser.service.PropertyParser;
import com.parser.util.AppProperty;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static AnnotationConfigApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        PropertyParser propertyParser = applicationContext.getBean(PropertyParser.class);
        String electronica = AppProperty.get("category.electronica");
        String zdorovie = AppProperty.get("category.zdorovie");
        String uroda = AppProperty.get("category.uroda");
        String property = AppProperty.get("class.parseTarget");

        propertyParser.getAllProductsByProp(property, zdorovie, uroda, electronica);
    }
}

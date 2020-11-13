package com.parser.service;

public interface PropertyParser {
    void parseCategoryByProp(String property, String category);

    void getAllProductsByProp(String property, String... categories);
}

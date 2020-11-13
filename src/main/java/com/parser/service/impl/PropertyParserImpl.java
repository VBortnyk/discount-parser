package com.parser.service.impl;

import com.parser.model.Product;
import com.parser.service.CsvHandler;
import com.parser.service.PageParser;
import com.parser.service.ProductParser;
import com.parser.service.PropertyParser;
import com.parser.util.AppProperty;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PropertyParserImpl implements PropertyParser {
    private final PageParser pageParser;
    private final ProductParser productParser;
    private final CsvHandler<Product> csvHandler;

    public PropertyParserImpl(PageParser pageParser, ProductParser productParser,
                              CsvHandler<Product> csvHandler) {
        this.pageParser = pageParser;
        this.productParser = productParser;
        this.csvHandler = csvHandler;
    }

    public void parseCategoryByProp(String property, String category) {
        List<String> discountLinks = pageParser.getTargetReferences(
                AppProperty.get("mainPage.url") + category, property, 100);
        List<Product> discountProducts = productParser.parseProductList(discountLinks);
        File file = csvHandler.create("products.csv", new Product());
        csvHandler.write(discountProducts, file);
    }

    public void getAllProductsByProp(String property, String...categories) {
        Arrays.stream(categories).forEach(category -> parseCategoryByProp(property, category));
    }
}

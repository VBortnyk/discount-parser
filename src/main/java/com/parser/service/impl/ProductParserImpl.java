package com.parser.service.impl;

import com.parser.model.Product;
import com.parser.service.PageParser;
import com.parser.service.ProductParser;
import com.parser.util.AppProperty;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ProductParserImpl implements ProductParser {
    private final PageParser pageParser;

    public ProductParserImpl(PageParser pageParser) {
        this.pageParser = pageParser;
    }

    @Override
    public Product parseProduct(String url) {
        Document document = pageParser.createDocument(url);

        return new Product.ProductBuilder()
                .withCategory(getCategory(document))
                .withName(getName(document))
                .withOldPrice(getOldPrice(document))
                .withNewPrice(getNewPrice(document))
                .withDiscountType(getDiscountType(document))
                .withInstallment(getInstallment(document))
                .withCoins(getCoins(document))
                .withBuyersVsItems(getByersVsItems(document))
                .withDeliveryInfo(getDeliveryInfo(document))
                .withAvailableItems(getAvailableItems(document))
                .build();
    }

    @Override
    public List<Product> parseProductList(List<String> urls) {
        return urls.stream().map(this::parseProduct).collect(Collectors.toList());
    }

    public String getCategory(Document document) {
        return document.getElementsByClass(AppProperty.get("product.category"))
                .text().split(" ")[1];
    }

    public String getName(Document document) {
        return document.getElementsByClass(AppProperty.get("product.name")).text();
    }

    public String getOldPrice(Document document) {
        return document.getElementsByClass(AppProperty.get("product.oldPrice")).text();

    }

    public String getNewPrice(Document document) {
        return document.getElementsByClass(AppProperty.get("product.newPrice")).text();
    }

    public String getDiscountType(Document document) {
        return document.getElementsByClass(AppProperty.get("product.discountType")).text();
    }

    public String getInstallment(Document document) {
        String info = document.getElementsByClass(AppProperty.get("product.installment")).text();
        return info.equals("") ? "no installment info" : info;
    }

    public String getCoins(Document document) {
        Elements coins = document.getElementsByClass(AppProperty.get("product.coins"));
        return coins.size() == 0 ? "0" : coins.text();
    }

    public String getByersVsItems(Document document) {
        return document.getElementsByClass(AppProperty.get("product.byersVsItems")).text();
    }

    public String getDeliveryInfo(Document document) {
        return document.getElementsByClass(AppProperty.get("product.deliveryInfo")).text();
    }

    public String getAvailableItems(Document document) {
        return document.getElementsByClass(AppProperty.get("product.availableItems"))
                        .text().replaceAll("[^0-9]", "");
    }
}

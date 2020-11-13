package com.parser.service;

import com.parser.model.Product;
import java.util.List;
import org.jsoup.nodes.Document;

public interface ProductParser {

    Product parseProduct(String url);

    List<Product> parseProductList(List<String> urls);

    String getCategory(Document document);

    String getName(Document document);

    String getOldPrice(Document document);

    String getNewPrice(Document document);

    String getDiscountType(Document document);

    String getInstallment(Document document);

    String getCoins(Document document);

    String getByersVsItems(Document document);

    String getAvailableItems(Document document);
}

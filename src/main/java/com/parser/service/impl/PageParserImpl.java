package com.parser.service.impl;

import com.parser.service.PageParser;
import com.parser.util.AppProperty;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class PageParserImpl implements PageParser {
    @Override
    public List<String> getTargetReferences(String url, String targetClass, int amount) {
        List<String> productLinks = getTargetReferences(url, targetClass);
        while (productLinks.size() < amount) {
            String nextPage = createDocument(url).select("a[rel=next]").attr("href");
            List<String> nextPageLinks = getTargetReferences(nextPage, targetClass);
            for (String link : nextPageLinks) {
                productLinks.add(link);
                if (productLinks.size() == amount) {
                    break;
                }
            }
        }
        return productLinks.stream().limit(amount).collect(Collectors.toList());
    }

    @Override
    public List<String> getTargetReferences(String url, String targetClass) {
        Document document = createDocument(url);
        Elements elements = document.getElementsByClass(AppProperty.get("class.parseTarget"));

        return elements.stream()
                .map(Element::siblingElements) //get all siblings
                .flatMap(Collection::stream) //unwrap all child elements of siblings
                .filter(element -> element.className().equals(AppProperty.get("product.link")))
                .map(Element::getAllElements) //get all child elements of class with reference
                .flatMap(Collection::stream) //unwrap all child elements of classes with references
                .filter(child -> child.hasAttr("href")) //filter children with references
                .map(element -> element.attr("href")) //get references as strings
                .collect(Collectors.toList());
    }

    @Override
    public Document createDocument(String url) {
        Document document;
        try {
            document = Jsoup.connect(url)
                    .userAgent(AppProperty.get("useragent"))
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create document while connecting to url: " + url
            );
        }
        return document;
    }
}

package com.parser.service;

import java.util.List;
import org.jsoup.nodes.Document;

public interface PageParser {

    List<String> getTargetReferences(String url, String targetClass, int amount);

    List<String> getTargetReferences(String url, String targetClass);

    Document createDocument(String url);
}

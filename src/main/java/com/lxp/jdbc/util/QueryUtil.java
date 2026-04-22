package com.lxp.jdbc.util;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QueryUtil {
    private static final Map<String, String> queries = new HashMap<>();

    static {
        loadQueries();
    }

    private static void loadQueries() {
        try (InputStream is = QueryUtil.class.getClassLoader().getResourceAsStream("queries.xml")) {
            if (is == null) {
                throw new IllegalStateException("queries.xml not found in classpath");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(is);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("query");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element queryElement = (Element) nodeList.item(i);
                String key = queryElement.getAttribute("key");
                String value = queryElement.getTextContent();
                if (key != null && !key.isBlank()) {
                    queries.put(key.trim(), normalize(value));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException("Failed to load queries.xml", e);
        }
    }

    public static String getQuery(String key) {
        String query = queries.get(key);
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Query not found for key: " + key);
        }
        return query;
    }

    private static String normalize(String query) {
        return query == null ? "" : query.trim().replaceAll("\\s+", " ");
    }
}

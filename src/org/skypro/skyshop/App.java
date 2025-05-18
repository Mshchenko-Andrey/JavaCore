package org.skypro.skyshop;

import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        Product[] products = {
                new SimpleProduct("Книга по Java", 500),
                new DiscountedProduct("Ноутбук", 50000, 10),
                new FixPriceProduct("Кабель USB")
        };


        Article[] articles = {
                new Article("Обзор ноутбуков", "Лучшие модели 2023 года"),
                new Article("Изучение Java", "Основы программирования")
        };


        SearchEngine engine = new SearchEngine(10);
        for (Product p : products) engine.add(p);
        for (Article a : articles) engine.add(a);


        System.out.println("=== Поиск 'Java' ===");
        Arrays.stream(engine.search("Java"))
                .filter(r -> r != null)
                .forEach(r -> System.out.println(r.getStringRepresentation()));

        System.out.println("\n=== Поиск 'ноутбук' ===");
        Arrays.stream(engine.search("ноутбук"))
                .filter(r -> r != null)
                .forEach(r -> System.out.println(r.getStringRepresentation()));
    }
}
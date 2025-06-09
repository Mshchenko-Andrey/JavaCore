package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.Searchable;

import java.util.Map;

public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket();
        SearchEngine engine = new SearchEngine();


        Product javaBook1 = new SimpleProduct("Java Book", 500);
        Product javaBook2 = new SimpleProduct("Java Book", 500);
        Product pythonBook = new SimpleProduct("Python Book", 600);
        Article javaArticle = new Article("Java News", "Latest Java features");


        basket.addProduct(javaBook1);
        basket.addProduct(javaBook2);
        basket.addProduct(pythonBook);


        engine.add(javaBook1);
        engine.add(pythonBook);
        engine.add(javaArticle);


        System.out.println("=== Тестирование корзины ===");
        basket.printBasket();


        System.out.println("\nУдаляем 'Java Book':");
        basket.removeProductsByName("Java Book").forEach(System.out::println);
        System.out.println("Осталось в корзине:");
        basket.printBasket();


        System.out.println("\n=== Тестирование поиска ===");
        Map<String, Searchable> results = engine.search("Java");
        System.out.println("Найдено " + results.size() + " результатов:");
        results.forEach((name, item) ->
                System.out.println(name + " (" + item.getContentType() + ")"));


        try {
            System.out.println("\nЛучший результат для 'Java':");
            System.out.println(engine.findBestMatch("Java").getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
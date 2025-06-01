package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket();
        SearchEngine engine = new SearchEngine();


        Product javaBook = new SimpleProduct("Java Book", 500);
        Product javaCourse = new DiscountedProduct("Java Course", 10000, 10);
        Product cable = new FixPriceProduct("USB Cable");
        Article javaArticle = new Article("Java News", "Latest Java updates");


        basket.addProduct(javaBook);
        basket.addProduct(javaCourse);
        basket.addProduct(javaCourse);
        basket.addProduct(cable);


        engine.add(javaBook);
        engine.add(javaCourse);
        engine.add(cable);
        engine.add(javaArticle);


        System.out.println("=== Демонстрация удаления ===");
        List<Product> removed = basket.removeProductsByName("Java Course");
        if (removed.isEmpty()) {
            System.out.println("Список пуст - продукты не найдены");
        } else {
            System.out.println("Удаленные продукты:");
            removed.forEach(System.out::println);
        }
        basket.printBasket();


        System.out.println("\n=== Демонстрация поиска ===");
        List<Searchable> results = engine.search("Java");
        System.out.println("Найдено " + results.size() + " результатов:");
        results.forEach(r -> System.out.println(r.getStringRepresentation()));


        try {
            System.out.println("\nЛучший результат для 'Java':");
            System.out.println(engine.findBestMatch("Java").getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}

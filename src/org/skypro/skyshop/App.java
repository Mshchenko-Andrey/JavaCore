package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.Searchable;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();
        SearchEngine engine = new SearchEngine();

        Product javaBook = new SimpleProduct("Java Book", 500);
        Product javaCourse = new DiscountedProduct("Java Course", 10000, 10);
        Product pythonBook = new SimpleProduct("Python Book", 600);
        Article javaArticle = new Article("Java News", "Latest Java features");

        basket.addProduct(javaBook);
        basket.addProduct(javaCourse);
        basket.addProduct(pythonBook);
        engine.add(javaBook);
        engine.add(javaCourse);
        engine.add(javaArticle);

        System.out.println("=== Содержимое корзины ===");
        basket.printBasket();

        System.out.println("\n=== Результаты поиска 'Java' ===");
        Set<Searchable> results = engine.search("Java");
        results.forEach(item -> System.out.println(item.getStringRepresentation()));

        try {
            System.out.println("\nЛучший результат для 'Java':");
            System.out.println(engine.findBestMatch("Java").getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}

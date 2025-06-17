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
        Product longNameProduct = new SimpleProduct("Very Long Product Name", 200);
        Article javaArticle = new Article("Java News", "Latest Java features");
        Article longArticle = new Article("Very Long Article Title", "Content");


        engine.add(javaBook);
        engine.add(javaBook);
        engine.add(javaCourse);
        engine.add(longNameProduct);
        engine.add(javaArticle);
        engine.add(longArticle);


        System.out.println("=== Результаты поиска 'Java' ===");
        Set<Searchable> javaResults = engine.search("Java");
        javaResults.forEach(item -> System.out.println(
                item.getName() + " (" + item.getContentType() + ")"
        ));


        System.out.println("\n=== Все элементы (сортировка по длине имени) ===");
        Set<Searchable> allResults = engine.search("");
        allResults.forEach(item -> System.out.println(
                item.getName() + " (" + item.getContentType() + ")"
        ));

        
        try {
            System.out.println("\nЛучший результат для 'Java':");
            System.out.println(engine.findBestMatch("Java").getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
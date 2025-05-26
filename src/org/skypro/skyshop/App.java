package org.skypro.skyshop;

import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.*;

public class App {
    public static void main(String[] args) {

        try {
            Product invalid = new SimpleProduct(" ", -100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания продукта: " + e.getMessage());
        }

        SearchEngine engine = new SearchEngine(10);
        engine.add(new SimpleProduct("Java книга", 500));
        engine.add(new Article("Java уроки", "Изучаем Java с нуля"));
        engine.add(new DiscountedProduct("Java курс", 10000, 10));

        try {
            Searchable best = engine.findBestMatch("Java");
            System.out.println("Лучший результат: " + best.getStringRepresentation());

            engine.findBestMatch("Python");
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
    }
}
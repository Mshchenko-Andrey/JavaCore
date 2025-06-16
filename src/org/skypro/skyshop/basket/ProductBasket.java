package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;
import java.util.stream.Collectors;

public class ProductBasket {
    private final Map<String, List<Product>> productsMap;

    public ProductBasket() {
        this.productsMap = new HashMap<>();
    }


    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }
        productsMap.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }


    public List<Product> removeProductsByName(String name) {
        if (name == null || name.isBlank()) {
            return Collections.emptyList();
        }
        List<Product> removed = productsMap.remove(name);
        return removed != null ? removed : Collections.emptyList();
    }

    public int getTotalPrice() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println("=== Содержимое корзины ===");
        productsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> entry.getValue().forEach(System.out::println));
        System.out.println("Итого: " + getTotalPrice() + " руб.");
    }


    public boolean containsProduct(String name) {
        return name != null && productsMap.containsKey(name);
    }

    public void clearBasket() {
        productsMap.clear();
    }

    public int getItemCount() {
        return productsMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    public Map<String, List<Product>> getBasketContents() {
        return Collections.unmodifiableMap(productsMap);
    }
}
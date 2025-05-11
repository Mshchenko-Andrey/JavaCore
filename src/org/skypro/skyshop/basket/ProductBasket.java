package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int BASKET_CAPACITY = 5;
    private final Product[] products;
    private int count;

    public ProductBasket() {
        this.products = new Product[BASKET_CAPACITY];
        this.count = 0;
    }

    public void addProduct(Product product) {
        if (count < BASKET_CAPACITY) {
            products[count++] = product;
        } else {
            System.out.println("Невозможно добавить продукт: корзина заполнена");
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printBasket() {
        if (count == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = 0;
        System.out.println("=== Содержимое корзины ===");

        for (int i = 0; i < count; i++) {
            Product p = products[i];
            System.out.println(p);
            if (p.isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
    }
}
package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {

        Product apple = new Product("Яблоко", 50);
        Product milk = new Product("Молоко", 80);
        Product bread = new Product("Хлеб", 40);
        Product eggs = new Product("Яйца", 120);
        Product cheese = new Product("Сыр", 200);
        Product butter = new Product("Масло", 150);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(apple);
        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(eggs);
        basket.addProduct(cheese);

        basket.addProduct(butter);

        basket.printBasket();

        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        System.out.println("Есть ли молоко в корзине? " + basket.containsProduct("Молоко"));
        System.out.println("Есть ли масло в корзине? " + basket.containsProduct("Масло"));

        basket.clearBasket();

        basket.printBasket();
        System.out.println("Общая стоимость пустой корзины: " + basket.getTotalPrice());
        System.out.println("Есть ли молоко в пустой корзине? " + basket.containsProduct("Молоко"));
    }
}
package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();


        Product simple = new SimpleProduct("Смартфон", 100000);
        Product discounted = new DiscountedProduct("Ноутбук", 70000, 10);
        Product fixedPrice = new FixPriceProduct("Кабель USB");


        basket.addProduct(simple);
        basket.addProduct(discounted);
        basket.addProduct(fixedPrice);

        basket.printBasket();
    }
}

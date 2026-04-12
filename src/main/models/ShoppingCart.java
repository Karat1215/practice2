package main.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalWithVAT(double vatRate) {
        double sum = 0;
        for (CartItem item : items) {
            sum += item.getTotal();
        }
        return sum * (1 + vatRate);
    }
}
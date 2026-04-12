package main.models;

public record OrderItem(
        String productId,
        String productName,
        double price,
        int quantity
) {
    public double getTotal() {
        return price * quantity;
    }
}
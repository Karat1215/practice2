package main.models;

public record CartItem(
        Product product,
        int quantity
) {
    public double getTotal() {
        return product.price() * quantity;
    }
}
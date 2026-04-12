package main.models;

import main.enums.OrderStatus;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<OrderItem> items;
    private final double totalAmount;
    private OrderStatus status;

    public Order(Customer customer, List<OrderItem> items, double totalAmount) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDING;
    }

    public String getId() { return id; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public double getTotalAmount() { return totalAmount; }
    public List<OrderItem> getItems() { return items; }
    public Customer getCustomer() { return customer; }
}
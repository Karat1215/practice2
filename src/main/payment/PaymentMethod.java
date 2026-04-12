package main.payment;

public sealed interface PaymentMethod permits OzonPayment, WildberriesPayment {
    void pay(double amount);
}
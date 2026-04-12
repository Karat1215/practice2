package main.payment;

public final class OzonPayment implements PaymentMethod {
    private final String paymentDetails;

    public OzonPayment(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Оплата через Ozon: " + paymentDetails);
        System.out.println("Сумма: " + amount + " руб.");
        System.out.println("Статус: УСПЕШНО");
    }
}
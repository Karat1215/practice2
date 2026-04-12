package main.payment;

public final class WildberriesPayment implements PaymentMethod {
    private final String walletId;

    public WildberriesPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Оплата через Wildberries: кошелек " + walletId);
        System.out.println("Сумма: " + amount + " руб.");
        System.out.println("Статус: УСПЕШНО");
    }
}
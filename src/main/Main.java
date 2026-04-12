package main;

import main.models.*;
import main.enums.*;
import main.payment.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("       ИНТЕРНЕТ-МАГАЗИН ЗАПУЩЕН");
        System.out.println("=========================================\n");

        // ===== 1. КАТАЛОГ ТОВАРОВ =====
        System.out.println("--- КАТАЛОГ ТОВАРОВ ---");
        Product phone = new Product("P001", "iPhone 15", 79900, ProductCategory.SMARTPHONE, 10);
        Product laptop = new Product("P002", "MacBook Air", 120000, ProductCategory.LAPTOP, 5);
        Product tablet = new Product("P003", "iPad Pro", 89900, ProductCategory.TABLET, 7);
        Product camera = new Product("P004", "Sony Camera", 55000, ProductCategory.CAMERA, 3);

        System.out.println("1. " + phone.name() + " - " + phone.price() + " руб.");
        System.out.println("2. " + laptop.name() + " - " + laptop.price() + " руб.");
        System.out.println("3. " + tablet.name() + " - " + tablet.price() + " руб.");
        System.out.println("4. " + camera.name() + " - " + camera.price() + " руб.\n");

        // ===== 2. ПОКУПАТЕЛЬ =====
        Customer customer = new Customer("C001", "Анна Смирнова", "anna@mail.ru");
        System.out.println("--- ПОКУПАТЕЛЬ ---");
        System.out.println("Имя: " + customer.getName());
        System.out.println("Email: " + customer.getEmail() + "\n");

        // ===== 3. КОРЗИНА =====
        System.out.println("--- КОРЗИНА ---");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(phone, 1);
        cart.addItem(laptop, 1);
        cart.addItem(camera, 1);

        for (CartItem item : cart.getItems()) {
            System.out.println(item.product().name() + " x" + item.quantity() +
                    " = " + item.getTotal() + " руб.");
        }

        double totalWithVAT = cart.getTotalWithVAT(0.2);
        System.out.println("ИТОГО с НДС 20%: " + totalWithVAT + " руб.\n");

        // ===== 4. ОФОРМЛЕНИЕ ЗАКАЗА =====
        System.out.println("--- ОФОРМЛЕНИЕ ЗАКАЗА ---");

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            orderItems.add(new OrderItem(
                    item.product().id(),
                    item.product().name(),
                    item.product().price(),
                    item.quantity()
            ));
        }

        Order order = new Order(customer, orderItems, totalWithVAT);

        System.out.println("Заказ #" + order.getId() + " создан!");
        System.out.println("Статус: " + order.getStatus());

        order.setStatus(OrderStatus.CONFIRMED);
        System.out.println("Статус изменен на: " + order.getStatus());
        order.setStatus(OrderStatus.SHIPPED);
        System.out.println("Статус изменен на: " + order.getStatus());

        System.out.println("\n--- ДЕТАЛИ ЗАКАЗА ---");
        System.out.println("Покупатель: " + order.getCustomer().getName());
        System.out.println("Товары:");
        for (OrderItem item : order.getItems()) {
            System.out.println("  - " + item.productName() + " x" + item.quantity() + " = " + item.getTotal() + " руб.");
        }
        System.out.println("Общая сумма: " + order.getTotalAmount() + " руб.\n");

        // ===== 5. ТРИ СЦЕНАРИЯ ОПЛАТЫ =====
        System.out.println("=========================================");
        System.out.println("          СЦЕНАРИИ ОПЛАТЫ");
        System.out.println("=========================================\n");

        System.out.println("--- СЦЕНАРИЙ 1: Ozon + Банковская карта ---");
        PaymentMethod ozonCard = new OzonPayment("Банковская карта Visa ****1234");
        ozonCard.pay(totalWithVAT);
        System.out.println();

        System.out.println("--- СЦЕНАРИЙ 2: Wildberries + Электронный кошелек ---");
        PaymentMethod wbWallet = new WildberriesPayment("WB Wallet #WB-777");
        wbWallet.pay(totalWithVAT);
        System.out.println();

        System.out.println("--- СЦЕНАРИЙ 3: Ozon + Наложенный платеж ---");
        PaymentMethod ozonCash = new OzonPayment("Наложенный платеж (оплата при получении)");
        ozonCash.pay(totalWithVAT);
        System.out.println();

        // ===== 6. ИТОГОВАЯ СВОДКА =====
        System.out.println("=========================================");
        System.out.println("          ИТОГОВАЯ СВОДКА");
        System.out.println("=========================================");
        System.out.println("Заказ #" + order.getId());
        System.out.println("Покупатель: " + customer.getName());
        System.out.println("Финальный статус: " + order.getStatus());
        System.out.println("Итого к оплате: " + order.getTotalAmount() + " руб.");
        System.out.println("=========================================");
        System.out.println("      СПАСИБО ЗА ПОКУПКУ!");
        System.out.println("=========================================");
    }
}
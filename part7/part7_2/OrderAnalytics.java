package part7.part7_2;

import java.util.*;
import java.util.stream.*;

record Order(String customer, String product, double price, int quantity, String category) {
    double total() {
        return price * quantity;
    }
}

public class OrderAnalytics {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order("Иван", "Ноутбук", 60000, 1, "Электроника"),
                new Order("Мария", "Телефон", 30000, 2, "Электроника"),
                new Order("Иван", "Книга", 500, 3, "Книги"),
                new Order("Петр", "Мышь", 1500, 1, "Электроника"),
                new Order("Анна", "Клавиатура", 4000, 1, "Электроника"),
                new Order("Мария", "Ручка", 100, 10, "Канцелярия"),
                new Order("Петр", "Монитор", 25000, 1, "Электроника"),
                new Order("Анна", "Тетрадь", 200, 5, "Канцелярия"),
                new Order("Иван", "Наушники", 5000, 2, "Электроника"),
                new Order("Мария", "Стол", 15000, 1, "Мебель")
        );

        // 1. Заказы дороже 5000
        System.out.println("=== Заказы дороже 5000 руб. ===");
        orders.stream()
                .filter(o -> o.total() > 5000)
                .forEach(System.out::println);

        // 2. Уникальные имена клиентов
        System.out.println("\n=== Уникальные клиенты ===");
        List<String> uniqueCustomers = orders.stream()
                .map(Order::customer)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(uniqueCustomers);

        // 3. Общая выручка
        System.out.println("\n=== Общая выручка ===");
        double totalRevenue = orders.stream()
                .mapToDouble(Order::total)
                .sum();
        System.out.println(totalRevenue);

        // 4. Самый дорогой заказ
        System.out.println("\n=== Самый дорогой заказ ===");
        orders.stream()
                .max(Comparator.comparingDouble(Order::total))
                .ifPresent(System.out::println);

        // 5. Количество заказов по категориям
        System.out.println("\n=== Количество заказов по категориям ===");
        Map<String, Long> categoryCount = orders.stream()
                .collect(Collectors.groupingBy(Order::category, Collectors.counting()));
        categoryCount.forEach((cat, count) -> System.out.println(cat + ": " + count));

        // 6. Средняя стоимость заказа по клиенту
        System.out.println("\n=== Средняя стоимость заказа по клиенту ===");
        Map<String, Double> avgByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::customer,
                        Collectors.averagingDouble(Order::total)));
        avgByCustomer.forEach((cust, avg) -> System.out.printf("%s: %.2f%n", cust, avg));

        // 7. Группировка на дорогие и дешёвые
        System.out.println("\n=== Группировка (дорогие > 3000) ===");
        Map<Boolean, List<Order>> partitioned = orders.stream()
                .collect(Collectors.partitioningBy(o -> o.total() > 3000));
        System.out.println("Дорогие: " + partitioned.get(true).size());
        System.out.println("Дешёвые: " + partitioned.get(false).size());

        // 8. Названия товаров дороже 1000 руб., уникальные, в верхнем регистре
        System.out.println("\n=== Товары дороже 1000 руб. ===");
        orders.stream()
                .filter(o -> o.price() > 1000)
                .map(Order::product)
                .distinct()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
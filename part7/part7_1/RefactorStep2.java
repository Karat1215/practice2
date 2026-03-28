package part7.part7_1;

import java.util.*;
import java.util.function.*;

public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине
        cities.sort(Comparator.comparingInt(String::length));

        // 2. Вывод каждого элемента
        cities.forEach(System.out::println);

        // 3. Преобразование в верхний регистр
        Function<String, String> toUpper = String::toUpperCase;

        // 4. Проверка длины > 5
        Predicate<String> isLong = s -> s.length() > 5; // нельзя заменить на ссылку

        // 5. Создание нового списка
        Supplier<List<String>> listFactory = ArrayList::new;

        // Использование
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);
    }
}
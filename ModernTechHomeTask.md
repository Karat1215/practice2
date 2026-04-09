# Консольное приложение электронной коммерции

## Дисциплина
Современные технологии программирования

## Группа: [ПИ24-3в]

## Студенты
| № | ФИО | Порядковый номер |
|---|-----|------------------|
| 1 | Ратундалова Каролина Игоревна | 13 |

---

## Запуск проекта

### Требования
- IntelliJ IDEA Community Edition
- Java 17 или выше

### Инструкция
1. Открыть проект в IntelliJ IDEA
2. Перейти в папку `src/main/`
3. Открыть файл `Main.java`
4. Нажать зеленую стрелку ▶️ `Run 'Main.main()'`

---

## Скриншот работы
<img width="409" height="754" alt="image" src="https://github.com/user-attachments/assets/03378af9-307f-45c6-a5ff-6e31346c9e2f" />
<img width="509" height="703" alt="image" src="https://github.com/user-attachments/assets/b39fc366-941c-43a9-bad1-cf7c7dd9bacb" />


---

## ERD-схема

<img width="1204" height="708" alt="image" src="https://github.com/user-attachments/assets/d8a23fb1-6b3a-41d1-85a6-f5819b485676" />


---

## Функциональные требования

| Функция | Реализовано |
|---------|-------------|
| Каталог товаров (4-5 товаров) | ✅ |
| Корзина (добавление/удаление) | ✅ |
| Расчет с НДС 20% | ✅ |
| Оформление заказа | ✅ |
| Изменение статуса заказа | ✅ |
| 3 сценария оплаты | ✅ |
| Итоговая сводка | ✅ |

---

## Сценарии оплаты

1. **Ozon + банковская карта** - оплата через Ozon с использованием банковской карты
2. **Wildberries + электронный кошелек** - оплата через Wildberries с использованием электронного кошелька
3. **Ozon + наложенный платеж** - оплата через Ozon при получении товара

---
## Использованные средства Java

| Средство | Где применено |
|----------|---------------|
| Classes | Customer, Order, ShoppingCart |
| Records | Product, CartItem, OrderItem |
| Interface | Payment |
| Sealed interface | PaymentMethod |
| Enums | OrderStatus, ProductCategory, PaymentStatus |
| Collections | ArrayList, HashMap |
| Паттерн "Стратегия" | OzonPayment, WildberriesPayment |

---

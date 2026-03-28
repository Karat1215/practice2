# Часть 9 — Эксперименты в jshell

## Как запустить jshell

Откройте терминал IntelliJ (View → Tool Windows → Terminal) и введите:
```
jshell
```
Для выхода: `/exit`

---

## Задание 9.1: Sealed-классы

### Команды (скопируйте и вставьте в jshell)

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
```

### Фактический вывод:

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
$4 ==> "Круг r=5.0"
```

### Вопрос: Что произойдёт при попытке создать `record Triangle(double a) implements Shape {}`?

**Ваш ответ:**
Будет ошибка компиляции, так как Triangle не указан в permits clause интерфейса Shape. Sealed-интерфейс разрешает наследование только тем классам/record, которые перечислены в permits.




---

## Задание 9.2: Цепочка лямбд

### Команды

```
import java.util.function.*
Function<String, String> trim = String::trim
Function<String, String> upper = String::toUpperCase
Function<String, String> exclaim = s -> s + "!"
var pipeline1 = trim.andThen(upper).andThen(exclaim)
var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline1.apply("  hello world  ")
pipeline2.apply("  hello world  ")
```

### Фактический вывод:

```
pipeline1.apply("  hello world  ")
$7 ==> "HELLO WORLD!"
pipeline2.apply("  hello world  ")
$8 ==> "HELLO WORLD!"
```

### Вопрос: Дают ли `andThen()` и `compose()` одинаковый результат? В каком случае результаты будут различаться?

**Ваш ответ:**
В данном примере результаты одинаковые, потому что порядок применения функций совпадает: trim → upper → exclaim. andThen() применяет функции слева направо, compose() — справа налево. Результаты различатся, если изменить порядок функций (например, exclaim.andThen(upper) даст "HELLO WORLD!!", а не "HELLO WORLD!").


---

## Задание 9.3: Сравнение EnumSet и HashSet

### Команды

```
enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
enumSet.contains(Color.RED)
hashSet.contains(Color.RED)
enumSet.getClass().getSimpleName()
hashSet.getClass().getSimpleName()
```

### Фактический вывод:

```
enumSet.contains(Color.RED)
$4 ==> true
hashSet.contains(Color.RED)
$5 ==> true
enumSet.getClass().getSimpleName()
$6 ==> "RegularEnumSet"
hashSet.getClass().getSimpleName()
$7 ==> "HashSet"
```

### Вопрос: Почему внутренний класс EnumSet называется `RegularEnumSet`? Что произойдёт, если enum будет иметь больше 64 констант?

**Ваш ответ:**
RegularEnumSet используется для enum с не более чем 64 константами (64 бита = long). Для enum с более чем 64 константами используется JumboEnumSet, который использует long[] для хранения битовых масок. Это оптимизация памяти и производительности.


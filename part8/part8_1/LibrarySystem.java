package part8.part8_1;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

// ========== 1. ENUM GENRE ==========
enum Genre {
    FICTION("Художественная литература"),
    SCIENCE("Научная литература"),
    HISTORY("История"),
    PROGRAMMING("Программирование"),
    ART("Искусство");

    private final String russianName;

    Genre(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}

// ========== 2. RECORD BOOK ==========
record Book(String title, String author, int year, Genre genre, double price) {

    public Book {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Автор книги не может быть пустым");
        }
        int currentYear = Year.now().getValue();
        if (year < 1450 || year > currentYear) {
            throw new IllegalArgumentException("Год издания должен быть между 1450 и " + currentYear);
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }
}

// ========== 3. SEALED INTERFACE LIBRARYITEM ==========
sealed interface LibraryItem permits PhysicalBook, EBook {
    String getInfo();
}

record PhysicalBook(Book book, String shelf) implements LibraryItem {
    @Override
    public String getInfo() {
        return String.format("[Физическая] %s - %s (%d) | Жанр: %s | Полка: %s | Цена: %.2f руб.",
                book.title(), book.author(), book.year(),
                book.genre().getRussianName(), shelf, book.price());
    }
}

record EBook(Book book, String format, double sizeMB) implements LibraryItem {
    @Override
    public String getInfo() {
        return String.format("[Электронная] %s - %s (%d) | Жанр: %s | Формат: %s | Размер: %.1f MB | Цена: %.2f руб.",
                book.title(), book.author(), book.year(),
                book.genre().getRussianName(), format, sizeMB, book.price());
    }
}

// ========== 4. CLASS LIBRARY ==========
class Library {
    private final List<LibraryItem> items = new ArrayList<>();

    public void add(LibraryItem item) {
        items.add(item);
    }

    public void printCatalog() {
        System.out.println("\n=== КАТАЛОГ БИБЛИОТЕКИ ===");
        for (LibraryItem item : items) {
            System.out.println(item.getInfo());
        }
    }

    public Map<Genre, List<LibraryItem>> groupByGenre() {
        return items.stream()
                .collect(Collectors.groupingBy(
                        item -> {
                            if (item instanceof PhysicalBook pb) {
                                return pb.book().genre();
                            } else {
                                return ((EBook) item).book().genre();
                            }
                        },
                        () -> new EnumMap<>(Genre.class),
                        Collectors.toList()
                ));
    }

    public double totalValue() {
        return items.stream()
                .mapToDouble(item -> {
                    if (item instanceof PhysicalBook pb) {
                        return pb.book().price();
                    } else {
                        return ((EBook) item).book().price();
                    }
                })
                .sum();
    }

    public Optional<LibraryItem> mostExpensive() {
        return items.stream()
                .max(Comparator.comparingDouble(item -> {
                    if (item instanceof PhysicalBook pb) {
                        return pb.book().price();
                    } else {
                        return ((EBook) item).book().price();
                    }
                }));
    }

    public List<String> authorsByGenre(Genre genre) {
        return items.stream()
                .filter(item -> {
                    if (item instanceof PhysicalBook pb) {
                        return pb.book().genre() == genre;
                    } else {
                        return ((EBook) item).book().genre() == genre;
                    }
                })
                .map(item -> {
                    if (item instanceof PhysicalBook pb) {
                        return pb.book().author();
                    } else {
                        return ((EBook) item).book().author();
                    }
                })
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}

// ========== 5. MAIN CLASS ==========
public class LibrarySystem {

    public static void main(String[] args) {
        Library library = new Library();

        // Создаём книги
        Book book1 = new Book("Война и мир", "Лев Толстой", 1869, Genre.FICTION, 1500);
        Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866, Genre.FICTION, 1200);
        Book book3 = new Book("Краткая история времени", "Стивен Хокинг", 1988, Genre.SCIENCE, 2000);
        Book book4 = new Book("Java: Полное руководство", "Герберт Шилдт", 2022, Genre.PROGRAMMING, 3500);
        Book book5 = new Book("История России", "Сергей Соловьёв", 1851, Genre.HISTORY, 2500);
        Book book6 = new Book("Искусство программирования", "Дональд Кнут", 1968, Genre.PROGRAMMING, 5000);
        Book book7 = new Book("Мона Лиза", "Леонардо да Винчи", 1503, Genre.ART, 800);
        Book book8 = new Book("Clean Code", "Роберт Мартин", 2008, Genre.PROGRAMMING, 2800);

        // Добавляем физические и электронные книги
        library.add(new PhysicalBook(book1, "A-12"));
        library.add(new PhysicalBook(book2, "A-15"));
        library.add(new EBook(book3, "PDF", 5.2));
        library.add(new PhysicalBook(book4, "B-03"));
        library.add(new EBook(book5, "EPUB", 8.5));
        library.add(new PhysicalBook(book6, "B-07"));
        library.add(new EBook(book7, "PDF", 25.3));
        library.add(new EBook(book8, "MOBI", 3.8));

        // 1. Вывод каталога
        library.printCatalog();

        // 2. Группировка по жанрам
        System.out.println("\n=== ГРУППИРОВКА ПО ЖАНРАМ ===");
        Map<Genre, List<LibraryItem>> grouped = library.groupByGenre();
        grouped.forEach((genre, items) -> System.out.println(genre.getRussianName() + ": " + items.size() + " книг(и)"));

        // 3. Общая стоимость
        System.out.println("\n=== ОБЩАЯ СТОИМОСТЬ ===");
        System.out.printf("Всего: %.2f руб.\n", library.totalValue());

        // 4. Самая дорогая книга
        System.out.println("\n=== САМАЯ ДОРОГАЯ КНИГА ===");
        library.mostExpensive().ifPresent(item -> System.out.println(item.getInfo()));

        // 5. Авторы жанра PROGRAMMING
        System.out.println("\n=== АВТОРЫ ЖАНРА ПРОГРАММИРОВАНИЕ ===");
        List<String> authors = library.authorsByGenre(Genre.PROGRAMMING);
        authors.forEach(System.out::println);
    }
}
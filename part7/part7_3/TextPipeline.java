package part7.part7_3;

import java.util.*;
import java.util.function.Function;

public class TextPipeline {

    public static void main(String[] args) {
        // ========== ЧАСТЬ А: Композиция функций ==========
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");
        Function<String, String> capitalize = s -> s.isEmpty() ? s :
                s.substring(0, 1).toUpperCase() + s.substring(1);

        // Композиция через andThen
        Function<String, String> normalize = trim
                .andThen(lower)
                .andThen(removeExtraSpaces)
                .andThen(capitalize);

        // Тестовые строки
        List<String> testStrings = Arrays.asList(
                "  привет   мир   ",
                "  JAVA   программирование   это   интересно  ",
                "   hello   world   from   java   ",
                "  функциональное   программирование   "
        );

        System.out.println("=== НОРМАЛИЗАЦИЯ СТРОК ===");
        for (String s : testStrings) {
            System.out.println("Исходная: \"" + s + "\"");
            System.out.println("Результат: \"" + normalize.apply(s) + "\"");
            System.out.println();
        }

        // ========== ЧАСТЬ В: Локальный класс WordCounter ==========
        class WordCounter {
            private final String text;

            WordCounter(String text) {
                this.text = text;
            }

            Map<String, Integer> getWordFrequency() {
                Map<String, Integer> freq = new HashMap<>();
                if (text == null || text.isBlank()) {
                    return freq;
                }
                String[] words = text.split("\\s+");
                for (String word : words) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
                return freq;
            }

            String mostFrequent() {
                Map<String, Integer> freq = getWordFrequency();
                if (freq.isEmpty()) {
                    return "";
                }
                return freq.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("");
            }
        }

        // Анализ нормализованного текста
        System.out.println("=== АНАЛИЗ ТЕКСТА ===");
        String sampleText = "  java   программирование   java   это   интересно   java   ";
        String normalized = normalize.apply(sampleText);

        System.out.println("Исходный текст: \"" + sampleText + "\"");
        System.out.println("Нормализованный: \"" + normalized + "\"");

        WordCounter counter = new WordCounter(normalized);
        Map<String, Integer> frequency = counter.getWordFrequency();

        System.out.println("\nЧастота слов:");
        frequency.forEach((word, count) -> System.out.println("  " + word + ": " + count));

        System.out.println("\nСамое частое слово: \"" + counter.mostFrequent() + "\"");
    }
}
package part2.part2_3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public interface Loggable {

    String getComponentName();

    private String formatTimestamp() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    default void log(String message) {
        System.out.printf("[%s] [%s] %s%n", formatTimestamp(), getComponentName(), message);
    }

    default void logError(String message) {
        System.out.printf("[%s] [%s] ОШИБКА: %s%n", formatTimestamp(), getComponentName(), message);
    }

    static String getLogLevel() {
        return "INFO";
    }
}
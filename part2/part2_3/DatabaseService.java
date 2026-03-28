package part2.part2_3;

public class DatabaseService implements Loggable {

    @Override
    public String getComponentName() {
        return "DatabaseService";
    }

    public void connect(String url) {
        log("Подключение к " + url);
        // имитация подключения
        log("Подключение установлено");
    }
}
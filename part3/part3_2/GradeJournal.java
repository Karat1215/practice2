package part3.part3_2;

public class GradeJournal {

    // Метод для вычисления среднего балла
    public static double average(int[] grades) {
        if (grades == null || grades.length == 0) return 0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    // Метод для нахождения максимальной оценки
    public static int max(int[] grades) {
        if (grades == null || grades.length == 0) return 0;
        int max = grades[0];
        for (int grade : grades) {
            if (grade > max) max = grade;
        }
        return max;
    }

    // Метод для нахождения минимальной оценки
    public static int min(int[] grades) {
        if (grades == null || grades.length == 0) return 0;
        int min = grades[0];
        for (int grade : grades) {
            if (grade < min) min = grade;
        }
        return min;
    }

    public static void main(String[] args) {
        // Массив имён
        String[] names = {"Алиса", "Борис", "Вера", "Глеб"};

        // Зубчатый массив оценок
        int[][] grades = {
                {5, 4, 5, 5, 3},      // Алиса
                {3, 3, 4},            // Борис
                {5, 5, 5, 5, 5, 4},   // Вера
                {4, 3, 4, 5}          // Глеб
        };

        System.out.println("=== Журнал оценок ===");

        String bestStudent = "";
        double bestAverage = 0;

        for (int i = 0; i < names.length; i++) {
            double avg = average(grades[i]);
            int minGrade = min(grades[i]);
            int maxGrade = max(grades[i]);

            System.out.printf("%s | Оценок: %d | Средний: %.2f | Мин: %d | Макс: %d%n",
                    names[i], grades[i].length, avg, minGrade, maxGrade);

            if (avg > bestAverage) {
                bestAverage = avg;
                bestStudent = names[i];
            }
        }

        System.out.printf("%nЛучший студент: %s (средний балл: %.2f)%n", bestStudent, bestAverage);
    }
}
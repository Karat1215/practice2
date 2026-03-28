package part5.part5_1;

import java.util.*;

// ========== ENUM GRADE ==========
enum Grade {
    A("Отлично", 4.0),
    B("Хорошо", 3.0),
    C("Удовлетворительно", 2.0),
    D("Неудовлетворительно", 1.0),
    F("Провал", 0.0);

    private final String description;
    private final double gpaValue;

    Grade(String description, double gpaValue) {
        this.description = description;
        this.gpaValue = gpaValue;
    }

    public String getDescription() {
        return description;
    }

    public double getGpaValue() {
        return gpaValue;
    }

    public static Grade fromScore(int score) {
        if (score >= 90) return A;
        if (score >= 80) return B;
        if (score >= 70) return C;
        if (score >= 60) return D;
        return F;
    }
}

// ========== RECORD STUDENT ==========
record Student(String name, int id) {
    public Student {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть положительным");
        }
    }
}

// ========== MAIN CLASS ==========
public class GradeSystem {

    public static void main(String[] args) {
        // Создаём студентов и оценки
        List<StudentScore> studentScores = Arrays.asList(
                new StudentScore(new Student("Алиса", 1), 95),
                new StudentScore(new Student("Борис", 2), 82),
                new StudentScore(new Student("Вера", 3), 75),
                new StudentScore(new Student("Глеб", 4), 65),
                new StudentScore(new Student("Дмитрий", 5), 55),
                new StudentScore(new Student("Елена", 6), 88),
                new StudentScore(new Student("Фёдор", 7), 45)
        );

        // Группировка через EnumMap
        EnumMap<Grade, List<Student>> grouped = new EnumMap<>(Grade.class);
        for (Grade grade : Grade.values()) {
            grouped.put(grade, new ArrayList<>());
        }

        for (StudentScore ss : studentScores) {
            Grade grade = Grade.fromScore(ss.score);
            grouped.get(grade).add(ss.student);
        }

        // Вывод сводки
        System.out.println("=== СВОДКА ОЦЕНОК ===");
        for (Grade grade : Grade.values()) {
            List<Student> students = grouped.get(grade);
            if (!students.isEmpty()) {
                System.out.println("\n" + grade + " (" + grade.getDescription() + ") GPA: " + grade.getGpaValue());
                System.out.println("   Количество: " + students.size());
                System.out.println("   Студенты: " + students);
            }
        }

        // Проходные оценки через EnumSet
        EnumSet<Grade> passingGrades = EnumSet.range(Grade.A, Grade.C);
        System.out.println("\n=== ПРОХОДНЫЕ ОЦЕНКИ ===");
        System.out.println(passingGrades);

        // Средний GPA
        double totalGpa = studentScores.stream()
                .mapToDouble(ss -> Grade.fromScore(ss.score).getGpaValue())
                .sum();
        double avgGpa = totalGpa / studentScores.size();
        System.out.printf("\n=== СРЕДНИЙ GPA ===\n%.2f\n", avgGpa);
    }

    // Вспомогательный класс для хранения студента и его оценки
    static class StudentScore {
        Student student;
        int score;

        StudentScore(Student student, int score) {
            this.student = student;
            this.score = score;
        }
    }
}
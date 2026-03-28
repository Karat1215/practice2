package part4.part4_2;

public class StringPoolLab {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        String s5 = s3.intern();
        String s6 = "Hel" + "lo";
        String half = "Hel";
        String s7 = half + "lo";

        System.out.println("=== String Pool Исследование ===\n");

        System.out.println("1. s1 и s2 (оба литералы)");
        System.out.println("   Результат: == " + (s1 == s2) + ", equals() " + s1.equals(s2) + "\n");

        System.out.println("2. s1 и s3 (литерал vs new String)");
        System.out.println("   Результат: == " + (s1 == s3) + ", equals() " + s1.equals(s3) + "\n");

        System.out.println("3. s3 и s4 (оба new String)");
        System.out.println("   Результат: == " + (s3 == s4) + ", equals() " + s3.equals(s4) + "\n");

        System.out.println("4. s1 и s5 (литерал vs s3.intern())");
        System.out.println("   Результат: == " + (s1 == s5) + ", equals() " + s1.equals(s5) + "\n");

        System.out.println("5. s1 и s6 (литерал vs конкатенация литералов)");
        System.out.println("   Результат: == " + (s1 == s6) + ", equals() " + s1.equals(s6) + "\n");

        System.out.println("6. s1 и s7 (литерал vs конкатенация с переменной)");
        System.out.println("   Результат: == " + (s1 == s7) + ", equals() " + s1.equals(s7) + "\n");

        System.out.println("7. StringBuilder vs s1");
        String sbString = "H" + "e" + "l" + "l" + "o";
        System.out.println("   Результат: == " + (s1 == sbString) + ", equals() " + s1.equals(sbString));
    }
}
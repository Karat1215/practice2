package part4.part4_1;

public class TextAnalyzer {
    private final String text;  // ← добавили final

    public TextAnalyzer(String text) {
        this.text = text;
    }

    public int wordCount() {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    public String longestWord() {
        if (text == null || text.trim().isEmpty()) return "";
        String[] words = text.trim().split("\\s+");
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public String reverseWords() {
        if (text == null || text.trim().isEmpty()) return "";
        String[] words = text.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) result.append(" ");
        }
        return result.toString();
    }

    public int countOccurrences(String target) {
        if (text == null || target == null) return 0;
        String lowerText = text.toLowerCase();
        String lowerTarget = target.toLowerCase();
        int count = 0;
        int index = 0;
        while ((index = lowerText.indexOf(lowerTarget, index)) != -1) {
            count++;
            index += lowerTarget.length();
        }
        return count;
    }

    public boolean isPalindrome() {
        if (text == null) return false;
        String cleaned = text.replaceAll("[^a-zA-Zа-яА-ЯЁ0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    @Override
    public String toString() {
        return text;
    }

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer("Java Programming is Fun and Java is Powerful");

        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("Java встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("is встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();

        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());
    }
}
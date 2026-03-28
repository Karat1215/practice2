package part2.part2_2;

public record BankTransfer(String bankName, String iban) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Перевод через %s: %.2f руб.", bankName, amount);
    }

    @Override
    public double fee(double amount) {
        return 50.0; // фиксированная комиссия
    }
}
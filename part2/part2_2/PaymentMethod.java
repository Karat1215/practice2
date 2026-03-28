package part2.part2_2;

public sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {
    String process(double amount);
    double fee(double amount);
}
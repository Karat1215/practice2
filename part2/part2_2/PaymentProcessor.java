package part2.part2_2;

public class PaymentProcessor {
    public static void describe(PaymentMethod pm) {
        switch (pm) {
            case CreditCard cc -> System.out.printf("Карта: владелец %s, номер *%s%n",
                    cc.holder(), cc.cardNumber().substring(cc.cardNumber().length() - 4));
            case BankTransfer bt -> System.out.printf("Банк: %s, IBAN: %s%n",
                    bt.bankName(), bt.iban());
            case CryptoWallet cw -> System.out.printf("Кошелёк: %s, валюта: %s%n",
                    cw.address(), cw.currency());
        }
    }
}
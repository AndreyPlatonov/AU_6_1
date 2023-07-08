package ru.netology.web.data;


import lombok.Value;
import ru.netology.web.page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        public String login;
        public String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    public static AuthInfo invalidAuthInfo() {
        return new AuthInfo("elena", "qwerty");
    }

    @Value
    public static class VerificationCode {
        public String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getInvalidVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("54321");
    }


    @Value
    public static class Card {
        public int id;
        public String cardNumber;
        public String cardID;
        public int cardBalance;

    }

    public static int sumTransfer() {

        return 100;
    }

    public static int sumInvalidTransfer(Card card) {

        return Math.abs(card.getCardBalance()) + 100;

    }

    public static Card getFirstCard() {
        return new Card(0, "5559 0000 0000 0001", DashboardPage.getCardIdById(0), DashboardPage.getCardBalance(DashboardPage.getCardIdById(0)));
    }

    public static Card getSecondCard() {
        return new Card(1, "5559 0000 0000 0002", DashboardPage.getCardIdById(1), DashboardPage.getCardBalance(DashboardPage.getCardIdById(1)));
    }
}

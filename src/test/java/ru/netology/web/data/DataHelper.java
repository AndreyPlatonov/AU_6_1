package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private int id;
        private String cardNumber;
        private String cardID;

    }

    public static Card getFirstCard(){
        return new Card(0, "5559 0000 0000 0001", DashboardPage.getCardIdById(0));
    }

    public static Card getSecondCard(){
        return new Card(1, "5559 0000 0000 0002", DashboardPage.getCardIdById(1));
    }
}

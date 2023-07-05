package ru.netology.web.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldTransferMoneyBetweenOwnCards() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage= LoginPage.validLogin(authInfo);
        var verificationCode=DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage=verificationPage.validVerify(verificationCode);
        var card =DataHelper.getFirstCard();
        var transferMoneyPage=dashboardPage.transfer(card);

    }
}

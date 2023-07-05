package ru.netology.web.test;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
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
        var cardFrom =DataHelper.getFirstCard();
        var cardTo=DataHelper.getSecondCard();
        var transferMoneyPage=dashboardPage.transfer(cardFrom);
        transferMoneyPage.checkBalanceForTransfer(1000, cardTo, cardFrom);

    }
}

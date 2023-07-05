package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
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
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardFrom = DataHelper.getFirstCard();
        var cardTo = DataHelper.getSecondCard();
        var transferMoneyPage = dashboardPage.transfer(cardFrom);

        int expectedCardFromBalance=cardFrom.getCardBalance()-100;
        int expectedCardToBalance=cardTo.getCardBalance()+100;

        transferMoneyPage.validTransfer(100, cardFrom, cardTo);

        int actualCardFromBalance= DashboardPage.getCardBalance(cardFrom.getCardID());
        int actualCardToBalance= DashboardPage.getCardBalance(cardTo.getCardID());

        Assertions.assertEquals(expectedCardFromBalance, actualCardFromBalance);
        Assertions.assertEquals(expectedCardToBalance, actualCardToBalance);


    }
}

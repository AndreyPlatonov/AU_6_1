package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferMoneyPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }


    @Test

    public void loginWrong() {

        var authInfo = DataHelper.invalidAuthInfo();
        LoginPage.invalidLogin(authInfo);

    }

    @Test
    void verificationWrong() {

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getInvalidVerificationCodeFor(authInfo);
        verificationPage.invalidVerify(verificationCode);

    }


    @Test
    public void otherAuthInfo() {

        var authInfo = DataHelper.getOtherAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage.emptyCards();

    }

    @Test

    public void shouldTransferMoneyBetweenOwnCardsFailed() {

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var cardFrom = DataHelper.getFirstCard();
        var cardTo = DataHelper.getSecondCard();
        var sumTransfer = DataHelper.sumInvalidTransfer(cardFrom);

        int expectedCardFromBalance = cardFrom.getCardBalance();
        int expectedCardToBalance = cardTo.getCardBalance();

        DashboardPage.transfer(cardFrom);

        TransferMoneyPage.invalidTransfer(sumTransfer, cardFrom);

        int actualCardFromBalance = DashboardPage.getCardBalance(cardFrom.getCardID());
        int actualCardToBalance = DashboardPage.getCardBalance(cardTo.getCardID());

        Assertions.assertEquals(expectedCardFromBalance, actualCardFromBalance);
        Assertions.assertEquals(expectedCardToBalance, actualCardToBalance);


    }

    @Test
    public void shouldTransferMoneyBetweenOwnCards() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var cardFrom = DataHelper.getFirstCard();
        var cardTo = DataHelper.getSecondCard();

        int expectedCardFromBalance = cardFrom.getCardBalance() - DataHelper.sumTransfer();
        int expectedCardToBalance = cardTo.getCardBalance() + DataHelper.sumTransfer();

        DashboardPage.transfer(cardFrom);

        if (!(TransferMoneyPage.checkBalanceForTransfer(DataHelper.sumTransfer(), cardFrom, cardTo))) {

            DataHelper.Card cardTmp = cardFrom;
            cardFrom = cardTo;
            cardTo = cardTmp;

            expectedCardFromBalance = cardFrom.getCardBalance() - DataHelper.sumTransfer();
            expectedCardToBalance = cardTo.getCardBalance() + DataHelper.sumTransfer();

        }


        TransferMoneyPage.validTransfer(DataHelper.sumTransfer(), cardFrom, cardTo);

        int actualCardFromBalance = DashboardPage.getCardBalance(cardFrom.getCardID());
        int actualCardToBalance = DashboardPage.getCardBalance(cardTo.getCardID());

        Assertions.assertEquals(expectedCardFromBalance, actualCardFromBalance);
        Assertions.assertEquals(expectedCardToBalance, actualCardToBalance);


    }
}

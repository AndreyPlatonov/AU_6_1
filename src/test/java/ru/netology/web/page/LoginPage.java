package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static SelenideElement loginField=$("[data-test-id=login] input");
    private static SelenideElement passwordField=$("[data-test-id=password] input");
    private static SelenideElement loginButton=$("[data-test-id=action-login].button");

    public static VerificationPage validLogin(DataHelper.AuthInfo infoClient) {

        loginField.setValue(infoClient.getLogin());
        passwordField.setValue(infoClient.getPassword());
        loginButton.click();

        return new VerificationPage();

    }
}

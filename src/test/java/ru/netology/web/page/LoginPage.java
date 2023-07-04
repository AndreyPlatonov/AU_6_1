package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static VerificationPage validLogin(DataHelper.AuthInfo infoClient) {

        $("[data-test-id=login] input").setValue(infoClient.getLogin());
        $("[data-test-id=password] input").setValue(infoClient.getPassword());
        $("[data-test-id=action-login].button").click();
        return new VerificationPage();

    }
}

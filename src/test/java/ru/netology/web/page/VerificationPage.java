package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {

        codeField.shouldBe(Condition.visible);

    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {

        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();

    }

    public void invalidVerify(DataHelper.VerificationCode verificationCode) {

        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! Неверно указан код! Попробуйте ещё раз.")).shouldBe(Condition.visible);


    }
}

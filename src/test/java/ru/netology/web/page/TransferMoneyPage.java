package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {

    private static SelenideElement header = $("[data-test-id=dashboard]");
    private static SelenideElement amount = $("[data-test-id=amount] input");
    private static SelenideElement cardTo = $("[data-test-id=to] input");
    private static SelenideElement cardFrom = $("[data-test-id=from] input");
    private static SelenideElement transferButton = $("[data-test-id=action-transfer].button");

    private  TransferMoneyPage() {

    }

    public TransferMoneyPage(DataHelper.Card card) {

        header.shouldBe(Condition.visible);
        cardTo.shouldHave(Condition.disabled);
        cardFrom.shouldHave(Condition.editable);
        amount.shouldHave(Condition.editable);
        cardTo.shouldHave(Condition.value("**** **** **** 000"+String.valueOf(card.getId()+1)));

    }

}

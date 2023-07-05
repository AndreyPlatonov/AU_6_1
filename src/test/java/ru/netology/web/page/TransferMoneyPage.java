package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {

    public SelenideElement header = $("[data-test-id=dashboard]");
    public static SelenideElement amount = $("[data-test-id=amount] input");
    public SelenideElement cardTo = $("[data-test-id=to] input");
    public static SelenideElement cardFrom = $("[data-test-id=from] input");
    public static SelenideElement transferButton = $("[data-test-id=action-transfer].button");




    public TransferMoneyPage(DataHelper.Card card) {

        header.shouldBe(Condition.visible);
        cardTo.shouldHave(Condition.disabled);
        cardFrom.shouldHave(Condition.editable);
        amount.shouldHave(Condition.editable);
        String s = Integer.toString(card.getId() + 1);
        cardTo.shouldHave(Condition.value("**** **** **** 000" + s));

    }

    public static boolean checkBalanceForTransfer(int amount, DataHelper.Card cardFrom, DataHelper.Card cardTo) {

        if (cardTo.getId() == cardFrom.getId() || amount <= cardFrom.getCardBalance()) {

            return true;
        }

        return false;

    }

    public  void validTransfer(int amountTransfer,  DataHelper.Card cardFromTransfer, DataHelper.Card cardToTransfer) {

        if (checkBalanceForTransfer(amountTransfer, cardFromTransfer, cardToTransfer)) {

            String s = Integer.toString(amountTransfer);
            amount.setValue(s);
            cardFrom.setValue(cardFromTransfer.getCardNumber());

            transferButton.click();



        }

    }
}

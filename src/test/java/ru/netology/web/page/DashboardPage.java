package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement header = $("[data-test-id=dashboard]");

    public DashboardPage() {

        header.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {

        String text = "";

        for (SelenideElement element : cards) {
            String cardID = element.getAttribute("data-test-id");
            if (cardID.equals(id)) {
                text = element.getText();
                break;
            }
        }

        return extractBalance(text);
    }



    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferMoneyPage transfer(DataHelper.Card id) {

        for (int i = 0; i < cards.size(); i++) {

            String cardId = "";
            cardId = cards.get(i).getAttribute("data-test-id");
            if (cardId.equals(id)) {
                cards.get(i).$("button").click();

                break;


            }
        }

        return new TransferMoneyPage();

    }
}

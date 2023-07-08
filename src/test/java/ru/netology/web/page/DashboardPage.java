package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private final static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    final SelenideElement header = $("[data-test-id=dashboard]");


    public DashboardPage() {

        header.shouldBe(Condition.visible);

    }

    public static boolean emptyCards() {

        return cards.isEmpty();
    }

    public static int getCardBalance(String id) {

        return extractBalance(cards.filterBy(Condition.attribute("data-test-id", id)).first().getText());

    }

    public static String getCardIdById(int id) {

        return cards.get(id).getAttribute("data-test-id");
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static TransferMoneyPage transfer(DataHelper.Card card) {

        cards.get(card.getId()).$("button").click();

        return new TransferMoneyPage(card);

    }
}

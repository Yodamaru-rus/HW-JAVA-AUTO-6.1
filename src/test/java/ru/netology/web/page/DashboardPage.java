package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection accountCashs = $$(".list__item DIV");
    private final ElementsCollection replenishButtons = $$("[data-test-id=action-deposit]");
    private final SelenideElement reloadButton = $("[data-test-id=action-reload]");

    public void verifyIsDashboardPage() {
        heading.shouldBe(visible);
    }

    public void topUpAccount(int to, int from, String summValue, String numberAccFrom) {
        to -= 1;
        from -= 1;
        verifyIsDashboardPage();
        int balanceAccountToBefore = extractBalance(accountCashs.get(to)),
                balanceAccountFromBefore = extractBalance(accountCashs.get(from));
        replenishButtons.get(to).click();
        new ReplenishPage(summValue, numberAccFrom);
        reloadButton.click();
        int summValueInt = Integer.parseInt(summValue.trim());
        accountCashs.get(to).shouldHave(text(" баланс: " + (balanceAccountToBefore + summValueInt) + " р."));
        accountCashs.get(from).shouldHave(text(" баланс: " + (balanceAccountFromBefore - summValueInt) + " р."));
    }

    public int extractBalance(SelenideElement element) {
        return Integer.parseInt(element.getText()
                .split("баланс:")[1]
                .replaceAll("[^0-9]", ""));
    }

}
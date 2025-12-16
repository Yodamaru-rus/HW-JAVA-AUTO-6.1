package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection accountCashs = $$(".list__item DIV");
    private final SelenideElement reloadButton = $("[data-test-id=action-reload]");

    public void verifyIsDashboardPage() {
        heading.shouldBe(visible);
    }

    public ReplenishPage replishClick(String testId) {
        accountCashs.findBy(attribute("data-test-id", testId)).$("Button").click();
        return new ReplenishPage();
    }

    public void reloadClick() {
        reloadButton.click();
    }

    public int getBalance(String testId) {
        return Integer.parseInt(accountCashs.findBy(attribute("data-test-id", testId)).getText()
                .split("баланс:")[1]
                .replaceAll("[^0-9]", ""));
    }

    public void checkSumm(String testId, String balance) {
        accountCashs.findBy(attribute("data-test-id", testId)).shouldHave(text(" баланс: " + balance + " р."));
    }
}
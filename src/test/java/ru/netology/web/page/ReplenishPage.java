package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReplenishPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]").sibling(0);
    private final SelenideElement summ = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private final SelenideElement errorPopup = $(".notification__content");
    String summValue, fromValue;

    public ReplenishPage(String summValue, String numberAccFrom) {
        this.summValue = summValue;
        this.fromValue = numberAccFrom;
        verifyIsReplenishPage();
    }

    public void verifyIsReplenishPage() {
        heading.shouldHave(text("Пополнение карты"));
    }

    public DashboardPage relenishAccount() {
        summ.setValue(summValue);
        from.setValue(fromValue);
        transferButton.click();
        return new DashboardPage();
    }

    public void cancelCashInAccounts() {
        cancelButton.click();
    }

    public void ifTranshisInvalid() {
        errorPopup.shouldHave(text("Ошибка! Произошла ошибка"));
    }
}
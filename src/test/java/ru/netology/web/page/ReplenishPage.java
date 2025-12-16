package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReplenishPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]").sibling(0);
    private final SelenideElement summ = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorPopup = $(".notification__content");

    public void verifyIsReplenishPage() {
        heading.shouldHave(text("Пополнение карты"));
    }

    public void relenishAccount(int summValue, String fromValue) {
        verifyIsReplenishPage();
        summ.setValue(String.valueOf(summValue));
        from.setValue(fromValue);
        transferButton.click();
    }

    public void ifTranshisInvalid() {
        errorPopup.shouldHave(visible, text("Ошибка! Произошла ошибка"));
    }
}
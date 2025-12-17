package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement login = $("[data-test-id=login] input");
    private final SelenideElement pass = $("[data-test-id=password] input");
    private final SelenideElement nextButton = $("[data-test-id=action-login]");
    private final SelenideElement errorPopup = $(".notification__content");

    public VerificationPage loginning(String loginValue, String passwordValue) {
        login.setValue(loginValue);
        pass.setValue(passwordValue);
        nextButton.click();
        return new VerificationPage();
    }

    public void ifLoginOrPassIsInvalid() {
        errorPopup.shouldHave(visible, text("Ошибка! Неверно указан логин или пароль"));
    }
}

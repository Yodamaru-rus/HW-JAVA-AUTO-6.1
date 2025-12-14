package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement verifyCode = $("[data-test-id=code] input");
    private final SelenideElement nextVerifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorPopup = $(".notification__content");

    public void verifyIsVerificationPage() {
        verifyCode.shouldBe(visible);
    }

    public DashboardPage validVerify(String verifyCodeValue) {
        verifyIsVerificationPage();
        verifyCode.setValue(verifyCodeValue);
        nextVerifyButton.click();
        return new DashboardPage();
    }

    public void ifCodeIsInvalid() {
        errorPopup.shouldHave(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}

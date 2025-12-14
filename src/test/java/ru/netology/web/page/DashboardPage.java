package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection accountCashs = $$(".CardList_cardBlock__gEjoa DIV");
    private final ElementsCollection replenishButtons = $$("[data-test-id=action-deposit]");
    private final SelenideElement reloadButton = $("[data-test-id=action-reload]");
    int to, from, balanceAccountToBefore, balanceAccountFromBefore;
    String summValue;

    public void verifyIsDashboardPage() {
        heading.shouldBe(visible);
    }

    public int getAccountID(String numberAccount) {
        String substr = numberAccount.substring(numberAccount.length() - 4);
        for (int i = 0; i < accountCashs.toArray().length; i++) {
            String a = accountCashs.get(i).getText().replaceAll("(?s).*?(\\d{4}).*", "$1");
            if (a.equals(substr)) {
                return i + 1;
            }
        }
        return -1;
    }

    public String getAccountNumber(int to) {
        return accountCashs.get(to).getText().substring(0, 19);
    }

    public ReplenishPage topUpAccount(int to, int from, String summValue, String numberAccFrom) {
        this.to = to - 1;
        this.from = from - 1;
        this.summValue = summValue;
        balanceAcc();
        replenishButtons.get(this.to).click();
        return new ReplenishPage(summValue, numberAccFrom);
    }

    public void successTransfer() {
        reloadButton.click();
        int summValueInt = Integer.parseInt(summValue.trim());
        accountCashs.get(to).shouldHave(text(" баланс: " + (balanceAccountToBefore + summValueInt) + " р."));
        accountCashs.get(from).shouldHave(text(" баланс: " + (balanceAccountFromBefore - summValueInt) + " р."));
    }

    public void balanceAcc() {
        balanceAccountToBefore = extractBalance(accountCashs.get(this.to));
        balanceAccountFromBefore = extractBalance(accountCashs.get(this.from));
    }

    public int extractBalance(SelenideElement element) {
        return Integer.parseInt(element.getText()
                .split("баланс:")[1]
                .replaceAll("[^0-9]", ""));
    }

}
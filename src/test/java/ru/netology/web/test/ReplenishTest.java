package ru.netology.web.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataGenerate;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.ReplenishPage;
import ru.netology.web.page.VerificationPage;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class ReplenishTest {

    @Test
    @DisplayName("Transh positive")
    void transhHundridRubpositive() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        int balanceBeforeTo = dashboardPage.getBalance(DataGenerate.getFirstCardInfo().getTestId());
        int balanceBeforeFrom = dashboardPage.getBalance(DataGenerate.getSecondCardInfo().getTestId());
        ReplenishPage replenishPage = dashboardPage.replishClick(DataGenerate.getFirstCardInfo().getTestId());
        replenishPage.verifyIsReplenishPage();
        int summValue = new Random().nextInt(balanceBeforeTo);
        replenishPage.relenishAccount(summValue, DataGenerate.getSecondCardInfo().getNumber());
        dashboardPage.reloadClick();
        String balanceAfterTo = String.valueOf(balanceBeforeTo + summValue);
        String balanceAfterFrom = String.valueOf(balanceBeforeFrom - summValue);
        dashboardPage.checkSumm(DataGenerate.getFirstCardInfo().getTestId(), balanceAfterTo);
        dashboardPage.checkSumm(DataGenerate.getSecondCardInfo().getTestId(), balanceAfterFrom);
    }

    @Test
    @DisplayName("Negative verification test")
    void verificationFailed() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        verificationPage.validVerify("1111");
        verificationPage.ifCodeIsInvalid();
    }

    @Test
    @DisplayName("Negative authorisation test")
    void authorisationFailed() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.Loginning("vasya", "1111111");
        loginPage.ifLoginOrPassIsInvalid();
    }

    @Test
    @DisplayName("Negative Transh more")
    void transhMoreAccountNegative() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        dashboardPage.verifyIsDashboardPage();
        int balanceBeforeTo = dashboardPage.getBalance(DataGenerate.getSecondCardInfo().getTestId());
        int balanceBeforeFrom = dashboardPage.getBalance(DataGenerate.getFirstCardInfo().getTestId());
        ReplenishPage replenishPage = dashboardPage.replishClick(DataGenerate.getSecondCardInfo().getTestId());
        replenishPage.verifyIsReplenishPage();
        int summValue = new Random().nextInt(100) + balanceBeforeTo;
        replenishPage.relenishAccount(summValue, DataGenerate.getFirstCardInfo().getNumber());
        dashboardPage.reloadClick();
        String balanceAfterTo = String.valueOf(balanceBeforeTo + summValue);
        String balanceAfterFrom = String.valueOf(balanceBeforeFrom - summValue);
        dashboardPage.checkSumm(DataGenerate.getFirstCardInfo().getTestId(), balanceAfterTo);
        dashboardPage.checkSumm(DataGenerate.getSecondCardInfo().getTestId(), balanceAfterFrom);
    }

    @Test
    @DisplayName("Negative Transh Mint")
    void transhMintAccountNegative() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        dashboardPage.verifyIsDashboardPage();
        int balanceBeforeTo = dashboardPage.getBalance(DataGenerate.getSecondCardInfo().getTestId());
        ReplenishPage replenishPage = dashboardPage.replishClick(DataGenerate.getSecondCardInfo().getTestId());
        replenishPage.verifyIsReplenishPage();
        int summValue = 10_000_000 + balanceBeforeTo;
        replenishPage.relenishAccount(summValue, DataGenerate.getFirstCardInfo().getNumber());
        replenishPage.ifTranshisInvalid();
    }
}

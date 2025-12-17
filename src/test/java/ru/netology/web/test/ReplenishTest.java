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
import static ru.netology.web.data.DataGenerate.*;

public class ReplenishTest {

    @Test
    @DisplayName("Transh positive")
    void transhHundridRubpositive() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.loginning(getAuthInfo().getLogin(), getAuthInfo().getPassword());
        DashboardPage dashboardPage = verificationPage.validVerify(getVerificationCode().getCode());
        int balanceBeforeTo = dashboardPage.getBalance(getFirstCardInfo().getTestId());
        int balanceBeforeFrom = dashboardPage.getBalance(getSecondCardInfo().getTestId());
        ReplenishPage replenishPage = dashboardPage.replishClick(getFirstCardInfo().getTestId());
        replenishPage.verifyIsReplenishPage();
        int summValue = new Random().nextInt(Math.abs(balanceBeforeFrom));
        replenishPage.relenishAccount(summValue, getSecondCardInfo().getNumber());
        dashboardPage.reloadClick();
        String balanceAfterTo = String.valueOf(balanceBeforeTo + summValue);
        String balanceAfterFrom = String.valueOf(balanceBeforeFrom - summValue);
        dashboardPage.checkSumm(getFirstCardInfo().getTestId(), balanceAfterTo);
        dashboardPage.checkSumm(getSecondCardInfo().getTestId(), balanceAfterFrom);
    }

    @Test
    @DisplayName("Negative verification test")
    void verificationFailed() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.loginning(getAuthInfo().getLogin(), getAuthInfo().getPassword());
        verificationPage.validVerify(getVerificationErrorCode().getCode());
        verificationPage.ifCodeIsInvalid();
    }

    @Test
    @DisplayName("Negative authorisation test")
    void authorisationFailed() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.loginning(getAuthErrorInfo().getLogin(), getAuthErrorInfo().getPassword());
        loginPage.ifLoginOrPassIsInvalid();
    }

    @Test
    @DisplayName("Negative Transh more")
    void transhMoreAccountNegative() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.loginning(getAuthInfo().getLogin(), getAuthInfo().getPassword());
        DashboardPage dashboardPage = verificationPage.validVerify(getVerificationCode().getCode());
        dashboardPage.verifyIsDashboardPage();
        int balanceBeforeFrom = dashboardPage.getBalance(getFirstCardInfo().getTestId());
        ReplenishPage replenishPage = dashboardPage.replishClick(getSecondCardInfo().getTestId());
        replenishPage.verifyIsReplenishPage();
        int summValue = new Random().nextInt(100) + balanceBeforeFrom;
        replenishPage.relenishAccount(summValue, getFirstCardInfo().getNumber());
        replenishPage.ifTranshisInvalid();
    }

    @Test
    @DisplayName("Negative Transh Mint")
    void transhMintAccountNegative() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.loginning(getAuthInfo().getLogin(), getAuthInfo().getPassword());
        DashboardPage dashboardPage = verificationPage.validVerify(getVerificationCode().getCode());
        dashboardPage.verifyIsDashboardPage();
        int balanceBeforeFrom = dashboardPage.getBalance(getFirstCardInfo().getTestId());
        ReplenishPage replenishPage = dashboardPage.replishClick(getSecondCardInfo().getTestId());
        replenishPage.verifyIsReplenishPage();
        int summValue = 10_000_000 + balanceBeforeFrom;
        replenishPage.relenishAccount(summValue, getFirstCardInfo().getNumber());
        replenishPage.ifTranshisInvalid();
    }
}

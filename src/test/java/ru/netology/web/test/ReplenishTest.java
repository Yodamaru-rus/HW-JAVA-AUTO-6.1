package ru.netology.web.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataGenerate;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.ReplenishPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;

public class ReplenishTest {

    @Test
    @DisplayName("Transh 100 rub positive")
    void transhHundridRubpositive() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        //ReplenishPage replenishPage =
        dashboardPage.topUpAccount(DataGenerate.getFirstCardInfo().getTestId(), DataGenerate.getSecondCardInfo().getTestId(), "100", DataGenerate.getSecondCardInfo().getNumber());
        //replenishPage.relenishAccount();
        //dashboardPage.successTransfer();
    }
/*
    @Test
    @DisplayName("Transh 1000 rub positive to 2 from 1")
    void transhHundridRubpositiveTo2From1() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        ReplenishPage replenishPage = dashboardPage.topUpAccount(DataGenerate.getSecondCardInfo().getTestId(), DataGenerate.getFirstCardInfo().getTestId(), "1000", DataGenerate.getFirstCardInfo().getNumber());
        replenishPage.relenishAccount();
        dashboardPage.successTransfer();
    }

    @Test
    @DisplayName("Negative verification test")
    void verificationFailed() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("1111");
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
        ReplenishPage replenishPage = dashboardPage.topUpAccount(DataGenerate.getSecondCardInfo().getTestId(), DataGenerate.getFirstCardInfo().getTestId(), "20001", DataGenerate.getFirstCardInfo().getNumber());
        replenishPage.relenishAccount();
        replenishPage.ifTranshisInvalid();
    }

    @Test
    @DisplayName("Negative Transh Mint")
    void transhMintAccountNegative() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        ReplenishPage replenishPage = dashboardPage.topUpAccount(DataGenerate.getSecondCardInfo().getTestId(), DataGenerate.getFirstCardInfo().getTestId(), "1000000", DataGenerate.getFirstCardInfo().getNumber());
        replenishPage.relenishAccount();
        replenishPage.ifTranshisInvalid();
    }

    @Test
    @DisplayName("incorrect account number")
    void incorrectAccountNumber() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        VerificationPage verificationPage = loginPage.Loginning("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");
        ReplenishPage replenishPage = dashboardPage.topUpAccount(DataGenerate.getFirstCardInfo().getTestId(), DataGenerate.getSecondCardInfo().getTestId(), "1", DataGenerate.getSecondCardInfo().getNumber());
        replenishPage.relenishAccount();
        replenishPage.ifTranshisInvalid();
    }*/
}

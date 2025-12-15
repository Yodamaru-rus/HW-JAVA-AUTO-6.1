package ru.netology.web.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataGenerate {

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", 1);
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", 2);
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class CardInfo {
        String number;
        int testId;
    }
}
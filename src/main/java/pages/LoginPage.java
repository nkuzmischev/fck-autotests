package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница входа в приложение
 */

public class LoginPage {

    @Step("Логинимся под {username}")
    public void logIn(String username, String password) {
        $("#user_name").sendKeys(username);
        $("#username_password").sendKeys(password);
        $("#bigbutton").click();
    }

}
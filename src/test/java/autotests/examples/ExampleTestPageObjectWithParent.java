package autotests.examples;

import autotests.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.User;

import static com.codeborne.selenide.Selenide.page;

/**
 * Демонстрационный тест с наследованием от базового класса и Page Object'ом
 */
@Link(name = "Полезная ссылка на аннотации", url = "https://habr.com/ru/company/sberbank/blog/359302/")
@TmsLink("64785859")
@Log4j
public class ExampleTestPageObjectWithParent extends BaseTest {

    @BeforeMethod
    @Step("Выполнение предусловий")
    public void beforeMethod() {
        //авторизация
        LoginPage loginPage = page(LoginPage.class);
        loginPage.logIn(User.TEST_USER.getUsername(), User.TEST_USER.getPassword());
    }

    @Test(description = "Демонстрационный тест")
    @Description("Проверка появления меню")
    public void exampleTestPageObjectWithParent() {
        log.info("Сообщение для логгера, вместо System.out.println()");

        //тестовый шаг 1
        menuAppearance();
    }

    @Step("Шаг1. Проверка появления меню")
    public void menuAppearance() {
        //наводимся на вкладку "Все"
        MainPage mainPage = page(MainPage.class);

        //наводимся на вкладку "Все"
        mainPage.getAllTab().hover();

        //проверяем, что выпадающее меню-список отображается
        mainPage.getAllDropdown().should(Condition.appear);
    }
}
package autotests.examples;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.User;

import static com.codeborne.selenide.Selenide.*;

/**
 * Демонстрационный успешный тест с использованием Page Object
 */
@Link(name = "Полезная ссылка на аннотации", url = "https://habr.com/ru/company/sberbank/blog/359302/")
@TmsLink("64785859")
public class ExamplePageObjectTest {

    @Test(description = "Демонстрационный тест")
    @Description("Проверка появления меню")
    public void exampleTestWithPageObject() {
        // открытие приложения
        open("http://fckproject.itfbgroup.ru/fcktest_001/");

        //авторизация
        LoginPage loginPage = page(LoginPage.class);
        loginPage.logIn(User.TEST_USER.getUsername(), User.TEST_USER.getPassword());

        //тестовый шаг
        menuAppearance();
    }

    @Step("Проверка появления меню")
    public void menuAppearance() {
        //наводимся на вкладку "Все"
        $x("//*[@class=\"desktop-toolbar\"]//*[@class=\"topnav all\"]").hover();

        //проверяем, что выпадающее меню-список отображается
        $x("//*[@class=\"topnav all\"]//ul[@class=\"dropdown-menu\"]").should(Condition.appear);
    }

    @AfterMethod(description = "Выход из приложения")
    public void shutDown() {
        MainPage mainPage = page(MainPage.class);
        mainPage.logOut();
    }
}

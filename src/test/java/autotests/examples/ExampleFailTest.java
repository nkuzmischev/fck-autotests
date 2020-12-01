package autotests.examples;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

/**
 * Демонстрационный тест с ошибкой при выполнении
 */
@Link(name = "Полезная ссылка на аннотации", url = "https://habr.com/ru/company/sberbank/blog/359302/")
@TmsLink("64785859")
public class ExampleFailTest {

    @Test(description = "Демонстрационный тест с ошибкой")
    @Description("Проверка появления меню")
    public void exampleFailTest() {
        // открытие приложения
        open("http://fckproject.itfbgroup.ru/auto/");

        //авторизация
        $("#user_name").sendKeys("test_auto");
        $("#username_password").sendKeys("159852Ff");
        $("#bigbutton").click();

        //тестовый шаг
        menuAppearance();
    }

    @Step("Проверка появления меню")
    public void menuAppearance() {
//        $x("//*[@class=\"desktop-toolbar\"]//*[@class=\"topnav all\"]").hover();
        $x("//*[@class=\"topnav all\"]//ul[@class=\"dropdown-menu\"]").should(Condition.appear);
    }

    @AfterMethod(description = "Выход из приложения")
    public void shutDown() {
        $x("//*[@class=\"desktop-bar\"]//*[@id=\"globalLinks\"]").click();
        $x("//*[@class=\"desktop-bar\"]//*[@id=\"logout_link\"]").click();
    }
}

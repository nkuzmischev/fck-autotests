package autotests;

import autotests.base.MyTestListener;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Базовый класс для всех тестов
 */

//аннотация для подключения логгера
@Log4j
@Listeners(MyTestListener.class)
public class BaseTest {

    @BeforeClass
    @Step("Начало тестирования")
    public void startUp() {
        // открытие приложения
        log.info("Начинаем тестирование");
        open("http://fckproject.itfbgroup.ru/fcktest_001/");
    }

    @AfterMethod(description = "Выход из приложения")
    public void shutDown() {
        log.info("Выход из приложения");
        MainPage mainPage = page(MainPage.class);
        mainPage.logOut();
    }
}

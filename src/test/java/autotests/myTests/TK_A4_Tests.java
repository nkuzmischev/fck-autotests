package autotests.myTests;

import autotests.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.User;

import static com.codeborne.selenide.Selenide.*;

public class TK_A4_Tests extends BaseTest {

    RequestPage requestPage;
    CreateRequestPage createRequestPage;
    FioEmail fioEmail;
    ViewingRequest viewingRequest;

    @Test(description = "Мой тест")
    public void beforeMethod() {
        //авторизация
        LoginPage loginPage = page(LoginPage.class);
        loginPage.logIn(User.TEST_USER.getUsername(), User.TEST_USER.getPassword());


//    @Step("Шаг 1. Проверка появления меню")

        //наводимся на вкладку "Все"
        MainPage mainPage = page(MainPage.class);

        //наводимся на вкладку "Все"
        mainPage.getAllTab().hover();

        //проверяем, что выпадающее меню-список отображается
        mainPage.getAllDropdown().should(Condition.appear);

        mainPage.menuAppeals("Обращения").click();


//    @Step("Шаг 4.")

        requestPage = page(RequestPage.class);
        sleep(2000);
        createRequestPage = requestPage.clickRequest();
        sleep(2000);
        fioEmail = createRequestPage.clickContactMen();


//    @Step("Шаг 5-6-7-8.")
        sleep(2000);
        createRequestPage = fioEmail.contactData();


        //   @Step("Шаг 9-17.")
        sleep(2000);
        createRequestPage = createRequestPage.fillingOutAppeal();
        viewingRequest = createRequestPage.fillingOutAppeal2();


        //   @Step()
        sleep(2000);
        Assert.assertEquals(viewingRequest.status(), "Не назначено");

        Assert.assertEquals(viewingRequest.state(), "Открыто");

    }


}

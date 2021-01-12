package autotests.myTests;

import autotests.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.User;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

@Link(name = "Ссылка на тест-кейс", url = "https://confluence.itfbgroup.ru/pages/viewpage.action?pageId=64785538")
@Log4j
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


        //   @Step(20-21)
        sleep(2000);
        Assert.assertEquals(viewingRequest.status(), "Не назначено");

        Assert.assertEquals(viewingRequest.state(), "Открыто");

        //18

        LocalDateTime deadlineDay = LocalDateTime.now();
        AdminPage adminPage = page(AdminPage.class);
        viewingRequest = page(ViewingRequest.class);

        ProductionCalendarPage productionCalendarPage = page(ProductionCalendarPage.class);

        mainPage.getAllTab().hover();

        mainPage.menuAppeals("Администрирование обращений").click();
        while (!adminPage.verificationRules("Входящий звонок", "Приглашение", "Мероприятия").isDisplayed()) {
            adminPage.getListViewNextButton().click();

        }
        adminPage.verificationRules("Входящий звонок", "Приглашение", "Мероприятия").click();


        deadlineDay = deadlineDay.plusDays(adminPage.getProcessingTime());

        mainPage.getAllTab().hover();

        mainPage.menuAppeals("Производственный календарь").click();
        productionCalendarPage.getDaysOrder().click();
        productionCalendarPage.getFirstDay().click();

        ArrayList<String> days = new ArrayList<>();


        while (!productionCalendarPage.getLastPage().isDisplayed()) {
            days.add(productionCalendarPage.getWeekend().getText());
            productionCalendarPage.getNextPage().click();
        }
        while (days.contains(deadlineDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                || deadlineDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || deadlineDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            deadlineDay = deadlineDay.plusDays(1);
        }

        open(url());
        String finalDeadLine = deadlineDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH"));
        String expDeadLine = viewingRequest.processingeadline().getText();
        Assert.assertEquals(finalDeadLine, expDeadLine.substring(0, expDeadLine.length() - 3));

        //19

        mainPage.getAllTab().hover();


        mainPage.menuAppeals("Администрирование обращений").click();
        while (!adminPage.verificationRules("Входящий звонок", "Приглашение", "Мероприятия").isDisplayed()) {
            adminPage.getListViewNextButton().click();

        }
        adminPage.verificationRules("Входящий звонок", "Приглашение", "Мероприятия").click();
        int weight = adminPage.getCaseWeight() + adminPage.getPriority();

        open(url());

        Assert.assertEquals(weight, Integer.parseInt(viewingRequest.getWeight().getText()));


    }


}




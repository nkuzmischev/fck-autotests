package autotests.myTests;

import autotests.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
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
public class TK_A4_Test extends BaseTest {

    RequestPage requestPage;
    CreateRequestPage createRequestPage;
    FioEmail fioEmail;
    ViewingRequest viewingRequest;
    MainPage mainPage;
    AdminPage adminPage;
    String viewingRequestUrl;

    @BeforeMethod
    @Step("Выполнение предусловий")
    public void beforeMethod() {
        //авторизация
        LoginPage loginPage = page(LoginPage.class);
        loginPage.logIn(User.TEST_USER.getUsername(), User.TEST_USER.getPassword());
    }

    @Test(description = "Мой тест")
    @Description("Проверка появления меню")
    public void myTest() {
        log.info("Открываем выпадающий список ВСЕ,Открываем модуль «Обращения»,Открываем форму создания обращения.");
        menuAppearance();

        log.info("Открываем форму поиска контактов.");
        openSearchContacts();
        log.info("Заполненяем поле «ФИО»., Заполненяем поле «Любой E-mail».,Нажимаем на кнопку «Найти», Выбраем физическое лицо из формы просмотра списка физических лиц.");
        fillFullName();

        log.info("Поле «Категория» заполненяем необходимым значением., Поле «Подтип» заполненяем необходимым значением.,Заполненяем поле «Тема», Заполненяем поле «Подтема», Поле «Описание» заполненяем необходимым значением., Открываем выпадающий список поля «Желаемый способ связи», Поле «Желаемый способ связи» заполненяем значением, Заполненяем поле «Email для связи», Нажать на кнопку «Сохранить и выйти ");
        fillCategory();

        log.info("Проверка поля «Крайний срок обработки");
        checkProcessingDeadline();
        log.info("Проверка заполнения поля «Вес обращения");
        checkWeightRequest();


        log.info("Проверка статуса обращения, Проверка состояния обращения");
        checkStatusRequest();


    }

    @Step("Шаг 1. Выполняем п.1, 2, 3 из ТК_А4")
    public void menuAppearance() {
        mainPage = page(MainPage.class);

        //наводимся на вкладку "Все"
        mainPage.getAllTab().hover();

        //проверяем, что выпадающее меню-список отображается
        mainPage.getAllDropdown().should(Condition.appear);

        mainPage.menuAppeals("Обращения").click();
    }

    @Step("Шаг 2. Выполняем п.4 из ТК_А4")
    public void openSearchContacts() {
        requestPage = page(RequestPage.class);

        createRequestPage = requestPage.clickRequest();

        fioEmail = createRequestPage.clickContactMen();

    }

    @Step("Шаг 3. Выполняем п.5, 6, 7, 8 из ТК_А4")
    public void fillFullName() {
        createRequestPage = fioEmail.contactData();

    }

    @Step("Шаг 4. Выполняем п. 9...17 из ТК_А4")
    public void fillCategory() {
        createRequestPage = createRequestPage.fillingOutAppeal();
        viewingRequest = createRequestPage.fillingOutAppeal2();
        viewingRequestUrl = url();
    }

    @Step("Шаг 5. Выполняем п.18 из ТК_А4 ")
    public void checkProcessingDeadline() {
        LocalDateTime deadlineDay = LocalDateTime.now();
        adminPage = page(AdminPage.class);
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

        open(viewingRequestUrl);
        String finalDeadLine = deadlineDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH"));
        String expDeadLine = viewingRequest.processingeadline().getText();
        Assert.assertEquals(finalDeadLine, expDeadLine.substring(0, expDeadLine.length() - 3));
    }

    @Step("Шаг 6. Выполняем п.19 из ТК_А4 ")
    public void checkWeightRequest() {
        mainPage.getAllTab().hover();


        mainPage.menuAppeals("Администрирование обращений").click();
        while (!adminPage.verificationRules("Входящий звонок", "Приглашение", "Мероприятия").isDisplayed()) {
            adminPage.getListViewNextButton().click();

        }
        adminPage.verificationRules("Входящий звонок", "Приглашение", "Мероприятия").click();
        int weight = adminPage.getCaseWeight() + adminPage.getPriority();

        open(viewingRequestUrl);

        Assert.assertEquals(weight, Integer.parseInt(viewingRequest.getWeight().getText()));
    }

    @Step("Шаг 7. Выполняем п.20, 21 из ТК_А4 ")
    public void checkStatusRequest() {
        Assert.assertEquals(viewingRequest.status(), "Не назначено");

        Assert.assertEquals(viewingRequest.state(), "Открыто");

    }


}




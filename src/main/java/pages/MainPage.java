package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

/**
 * Домашняя страница приложения
 */
public class MainPage {

    @FindBy(xpath = "//*[@class=\"desktop-bar\"]//*[@id=\"globalLinks\"]")
    private SelenideElement profileDropdown;

    @FindBy(xpath = "//*[@class=\"desktop-bar\"]//a[@id=\"logout_link\"]")
    private SelenideElement logoutLink;

    @FindBy(xpath = "//*[@class=\"desktop-toolbar\"]//*[@class=\"topnav all\"]")
    @Getter
    private SelenideElement allTab;


    @FindBy(xpath = "//*[@class=\"topnav all\"]//ul[@class=\"dropdown-menu\"]")
    //аннотация для генерации геттера
    @Getter
    private SelenideElement allDropdown;


    @Step("Выход из приложения")
    public void logOut() {
        profileDropdown.click();
        logoutLink.click();
    }

}
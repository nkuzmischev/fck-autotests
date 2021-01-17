package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

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

    @FindBy(xpath = "//*[@id=\"grouptab_1\"]")
    private SelenideElement allButton;

    @FindBy(xpath = "//*[@id=\"toolbar\"]/ul[1]/li[3]/span[2]/ul[1]/li[22]/a[1]")
    private SelenideElement requestButton;

    public SelenideElement menuAppeals(String menu) {
        return allDropdown.$x(".//a[contains(text(),'" + menu + "')]");
    }



    public RequestPage clickRequest() {
        allButton.click();
        requestButton.click();
        RequestPage page = page(RequestPage.class);
        return page;
    }



    public void logOut() {
        profileDropdown.click();
        logoutLink.click();
    }

}
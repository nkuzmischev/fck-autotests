package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class RequestPage {


    @FindBy(xpath = "//*[@id=\"actionMenuSidebar\"]/ul[1]/li[1]/a[1]/div[2]")
    private SelenideElement createRequestButton;

    @Step("3.Обращение")
    public CreateRequestPage clickRequest() {
        createRequestButton.click();
        CreateRequestPage page = page(CreateRequestPage.class);
        return page;
    }

}

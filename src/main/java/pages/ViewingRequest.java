package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class ViewingRequest {
    @FindBy(xpath = "//*[@id=\"tab-content-0\"]/div[2]/div[2]/div[2]")
    private SelenideElement status;

    @FindBy(xpath = "//*[@id=\"tab-content-0\"]/div[2]/div[1]/div[2]")
    private SelenideElement state;

    @Step("20 шаг")
    public String status() {
        return status.getText();


    }

    @Step("21 шаг")
    public String state() {
        return state.getText();


    }


}

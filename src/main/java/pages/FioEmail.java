package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

public class FioEmail {

    @FindBy(xpath = "//*[@id=\"full_name_advanced\"]")
    private SelenideElement fieldFIO;

    @FindBy(xpath = "//*[@id=\"email_advanced\"]")
    private SelenideElement fieldEmail;

    @FindBy(xpath = "//*[@id=\"search_form_submit\"]")
    private SelenideElement findButton;

    public SelenideElement findContact(String email, String fullName) {
        return $x(".//td[contains(text(),'" + email + "')]/..//a[contains(text(),'" + fullName + "')]");
    }


    public CreateRequestPage contactData() {
        fieldFIO.sendKeys("Ласточкин Борис Васильевич");
        fieldEmail.sendKeys("testlkvp@mail.ru");
        findButton.click();
        findContact("testlkvp@mail.ru", "Ласточкин Борис Васильевич").click(); //8 step
        CreateRequestPage page = page(CreateRequestPage.class);
        switchTo().window(0);
        return page;
    }

}

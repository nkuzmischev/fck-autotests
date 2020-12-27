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

    //@FindBy(xpath = "body/table[4]/tbody[1]/tr[1]/td[1]/a[1]")
    // private SelenideElement personButton;
    public SelenideElement findContact(String email, String fullName) {
        return $x(".//td[contains(text(),'" + email + "')]/..//a[contains(text(),'" + fullName + "')]");
    }


    @Step("5-6-7-8 Шаг. Ввести ФИО и почту")
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

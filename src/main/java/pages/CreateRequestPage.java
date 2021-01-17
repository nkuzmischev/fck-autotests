package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

public class CreateRequestPage {
    @FindBy(xpath = "//*[@id=\"contact_created_by_name\"]")
    private SelenideElement fieldContact;
    @FindBy(xpath = "//*[@id=\"btn_contact_created_by_name\"]/span[1]")
    private SelenideElement contactButton;

    //9
    @FindBy(xpath = "//*[@id=\"category\"]")
    private SelenideElement fieldCategory;
    //10
    @FindBy(xpath = "//*[@id=\"subtype\"]")
    private SelenideElement fieldSubtype;
    //11
    @FindBy(xpath = "//*[@id=\"subject\"]")
    private SelenideElement fieldSubject;
    //12
    @FindBy(xpath = "//*[@id=\"subsubject\"]")
    private SelenideElement fieldSubSubject;

    @FindBy(id = "tinymce")
    @Getter
    private SelenideElement fieldDetailPanel;

    //14
    @FindBy(xpath = "//*[@id=\"connect\"]")
    private SelenideElement fieldConnect;
    //15
    @FindBy(xpath = "//*[@id=\"contact_emails\"]")
    private SelenideElement fieldContactEmails;


    //17
    @FindBy(xpath = "(//*[@id='SAVE'])[2]")
    @Getter
    private SelenideElement buttonSaveExit;


    public FioEmail clickContactMen() {
        fieldContact.sendKeys("Ласточкин Борис Васильевич");
        contactButton.click();
        FioEmail page = page(FioEmail.class);
        switchTo().window(1);
        return page;
    }


    public CreateRequestPage fillingOutAppeal() {
        fieldCategory.click();
        fieldCategory.$x("./option[contains(text(),'" + "Сотрудник ФЦК" + "')]").click();
        fieldSubtype.click();
        fieldSubtype.$x("./option[contains(text(),'" + "Приглашение" + "')]").click();
        fieldSubject.click();
        fieldSubject.$x("./option[contains(text(),'" + "Мероприятия" + "')]").click();
        fieldSubSubject.click();
        fieldSubSubject.$x("./option[contains(text(),'" + "Другое" + "')]").click();
        switchTo().frame(2);
        getFieldDetailPanel().sendKeys("Текст описания");
        switchTo().defaultContent();
        fieldConnect.click();
        fieldConnect.$x("./option[contains(text(),'" + "Email" + "')]").click();
        sleep(1000);
        CreateRequestPage page = page(CreateRequestPage.class);
        return page;
    }


    public ViewingRequest fillingOutAppeal2() {

        fieldContactEmails.click();
        fieldContactEmails.$x("./option[contains(text(),'" + "testlkvp@mail.ru" + "')]").click();
        getButtonSaveExit().click();

        ViewingRequest page = page(ViewingRequest.class);
        return page;
    }


}

package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class ProductionCalendarPage {
    @FindBy(xpath = "//*[@id='listViewStartButton_top']")
    @Getter
    private SelenideElement daysOrder;


    @FindBy(xpath = "(//*[@field='name']/b)[1]")
    @Getter
    private SelenideElement firstDay;

    @FindBy(xpath = "//*[@field= 'festive_date']")
    @Getter
    private SelenideElement weekend;


    @FindBy(xpath = "(//*[@title=\"След.\" and @disabled='true'])[2]")
    @Getter
    private SelenideElement lastPage;
    @FindBy(xpath = "(//*[@title=\"След.\" ])[2]")
    @Getter
    private SelenideElement nextPage;


}

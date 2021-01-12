package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AdminPage {
    HashMap<String, Integer> priority = new HashMap<String, Integer>() {{
        put("Высокий", 3);
        put("Средний", 2);
        put("Низкий", 1);
    }};

    public SelenideElement verificationRules(String type, String subtype, String subject) {
        return $x("//*[contains(text(), '" + type + " > " + subtype + " > " + subject + "')]");
    }

    public SelenideElement caseWeight() {
        return $("#case_weight");
    }

    public SelenideElement processingTime() {
        return $("#processing_time");
    }


    public SelenideElement casePriority() {
        return $x("//div[@field=\"case_priority\"]");
    }

    public int getCaseWeight() {
        return Integer.parseInt(caseWeight().getText());
    }

    public int getPriority() {

        return priority.get(casePriority().getText());

    }

    public int getProcessingTime() {
        return Integer.parseInt(processingTime().getText());
    }

    @FindBy(id = "listViewNextButton_top")
    @Getter
    private SelenideElement listViewNextButton;


}


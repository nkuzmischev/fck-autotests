package examples;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class ExampleTest {

    @Test
    public void exampleTest(){
        open("http://fckproject.itfbgroup.ru/fcktest_001/");
        $("#user_name").sendKeys("test_auto");
        $("#username_password").sendKeys("159852Ff");
        $("#bigbutton").click();

        menuAppearance();
    }

    @Step("Проверка появления меню")
    public void menuAppearance(){
        $x("//*[@class=\"desktop-toolbar\"]//*[@class=\"topnav all\"]").hover();
        $x("//*[@id=\"mobile_menu\"]").should(Condition.exist);
    }

    @AfterMethod
    public void shutDown(){
        $x("//*[@class=\"desktop-bar\"]//*[@id=\"globalLinks\"]").click();
        $x("//*[@class=\"desktop-bar\"]//*[@id=\"logout_link\"]").click();
    }
}

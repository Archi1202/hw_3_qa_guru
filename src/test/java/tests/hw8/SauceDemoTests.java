package tests.hw8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.chrome.ChromeOptions;
import tests.TestBase;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SauceDemoTests extends TestBase {

    @ParameterizedTest(name = "Verify that login process successfully works for {0}")
    @ValueSource(strings = {
            "standard_user",
            "visual_user"
    })
    @Tag("SMOKE")
    void loginSuccessTest(String userName){
        open("https://www.saucedemo.com");
        $("#user-name").setValue(userName);
        $("#password").setValue("secret_sauce");
        $("#login-button").click();
        $(".app_logo").shouldHave(text("Swag Labs"));
    }

    @CsvFileSource (resources = "/negativeLoginVerificationTest.csv")
    @ParameterizedTest(name = "Check that after invalid login with {0} there is an error")
    @Tag("NEGATIVE")
    void negativeLoginVerificationTest(String invalidLogin,String password){
        open("https://www.saucedemo.com");
        $("#user-name").setValue(invalidLogin);
        $("#password").setValue(password).pressEnter();
        $("h3[data-test='error']").isDisplayed();
    }
    public static void setup() {
        ChromeOptions options = new ChromeOptions();

        // Disable password manager popup
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        Configuration.browserCapabilities = options;
    }
    @CsvSource(value = {"Sauce Labs Backpack",
            "Sauce Labs Bike Light"})
    @ParameterizedTest(name = "Check that user is able to open specific product {0} from the list")
    @Tag("SMOKE")
    void openProductItemsTest(String value){
        setup();
        open("https://www.saucedemo.com");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $$("[data-test='inventory-item-description'] [data-test='inventory-item-name']").
                findBy(text(value)).click();
        $("#add-to-cart").shouldBe(visible);
    }
}

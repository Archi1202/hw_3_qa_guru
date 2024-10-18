package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void beforeAll(){

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = "https://" + System.getProperty("LOGIN") + "@" + System.getProperty("REMOTE_URL");
        Configuration.timeout = 10000;
        Configuration.browser = System.getProperty("BROWSER_NAME","chrome");
        Configuration.browserSize = System.getProperty("BROWSER_SIZE");
        Configuration.browserVersion = System.getProperty("BROWSER_VERSION");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach(){
        closeWebDriver();
    }
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        if(!Configuration.browser.equals("firefox")){
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();

    }
}

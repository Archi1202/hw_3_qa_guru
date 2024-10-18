package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class TestBase {
    private final RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUp(){

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://" + System.getProperty("login") + "@" + System.getProperty("remote");
        Configuration.timeout = 10000;
        Configuration.browser = System.getProperty("browserName","chrome");
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browserVersion = System.getProperty("browserVersion");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    protected void doBeforeEach() {
        step("Open the Registration Form Page", () -> {
            registrationPage.openPage();
        });

        step("Remove banners", () -> {
            registrationPage.removeBanners();
        });
    }

    @AfterEach
    protected void tearDown() {
        Attach.screenshotAs("Last Screenshot");
        if (!Configuration.browser.equals("firefox")) {
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}

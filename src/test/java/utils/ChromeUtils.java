package utils;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeUtils {

    // Static method to set ChromeOptions
    public static ChromeOptions removeChromeAlertNotification() {
        ChromeOptions options = new ChromeOptions();

        // Disable password manager popup
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        return options;
    }

    // Static setup method to apply the options
    public static void removeChromeAlert() {
        Configuration.browserCapabilities = removeChromeAlertNotification();
    }
}
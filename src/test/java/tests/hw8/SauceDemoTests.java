package tests.hw8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SauceLabsSwag.SauceDemoPage;
import tests.TestBase;
import utils.ChromeUtils;

public class SauceDemoTests extends TestBase {

    SauceDemoPage sauceDemoPage = new SauceDemoPage();

    @ParameterizedTest(name = "Verify that login process successfully works for {0}")
    @ValueSource(strings = {
            "standard_user",
            "visual_user"})
    @Tag("SMOKE")
    void loginSuccessTest(String userName){
        sauceDemoPage.openPage()
                .enterUsername(userName)
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .checkSwagLogoDisplayed("Swag Labs");
    }

    @CsvFileSource (resources = "/negativeLoginVerificationTest.csv")
    @ParameterizedTest(name = "Check that after invalid login with {0} there is an error displayed on Login Page")
    @Tag("NEGATIVE")
    void negativeLoginVerificationTest(String invalidLogin,String password){
        sauceDemoPage.openPage()
                .enterUsername(invalidLogin)
                .enterPassword(password)
                .clickLoginButton()
                .checkErrorMessageDisplayed();

    }


    @BeforeAll
    public static void setupTest() {
        ChromeUtils.removeChromeAlert();
    }
    @CsvSource(value =
            {"standard_user, secret_sauce, Sauce Labs Backpack",
            "standard_user, secret_sauce, Sauce Labs Bike Light"})
    @ParameterizedTest(name = "Check that user is able to open page of specific product {2} from the inventory list")
    @Tag("SMOKE")
    void openProductItemTest(String userName,String password, String productName){

        sauceDemoPage.openPage()
                .enterUsername(userName)
                .enterPassword(password)
                .clickLoginButton()
                .clickOnProductName(productName)
                .checkAddToCartButtonDisplayed();
    }
}

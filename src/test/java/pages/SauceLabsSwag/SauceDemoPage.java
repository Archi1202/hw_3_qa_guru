package pages.SauceLabsSwag;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SauceDemoPage {
    private final SelenideElement userNameInputLocator = $("#user-name"),
            passwordInputLocator = $("#password"),
            loginButton = $("#login-button"),
            logoSwagLabsLocator = $(".app_logo"),
            errorMessageLocator = $("h3[data-test='error']"),
            addToCartButton = $("#add-to-cart");

    public SauceDemoPage openPage(){
        open("https://www.saucedemo.com");
        return this;
    }

    public SauceDemoPage enterUsername(String userName){
        userNameInputLocator.setValue(userName);
        return this;
    }

    public SauceDemoPage enterPassword(String password){
        passwordInputLocator.setValue(password);
        return this;
    }

    public SauceDemoPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    public SauceDemoPage clickOnProductName(String productName){
        $$("[data-test='inventory-item-description'] [data-test='inventory-item-name']").findBy(text(productName)).click();
        return this;
    }

    public SauceDemoPage checkAddToCartButtonDisplayed() {
        addToCartButton.shouldBe(visible);
        return this;
    }

    public SauceDemoPage checkSwagLogoDisplayed(String logoName) {
        logoSwagLabsLocator.shouldHave(text(logoName));
        return this;
    }

    public SauceDemoPage checkErrorMessageDisplayed() {
        errorMessageLocator.isDisplayed();
        return this;
    }
}

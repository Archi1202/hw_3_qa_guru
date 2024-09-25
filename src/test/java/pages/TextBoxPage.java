package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    private final SelenideElement
            fullNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            addressCurrentInput = $("#currentAddress"),
            addressPermanentInput = $("#permanentAddress"),
            submitInput = $("button#submit"),
            resultTable = $("div#output");

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public TextBoxPage setUserName(String userName) {
        fullNameInput.setValue(userName);
        return this;
    }

    public TextBoxPage setUserEmail(String email) {
        userEmailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String CuAddress) {
        addressCurrentInput.setValue(CuAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String PeAddress) {
        addressPermanentInput.setValue(PeAddress);
        return this;
    }

    public void clickSubmit() {
        submitInput.click();
    }

    public TextBoxPage checkTheTextBoxResult(String result){
        resultTable.shouldHave(text(result));
        return this;
    }

}
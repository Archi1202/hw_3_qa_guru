package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;

public class TextBoxResultComponent {


    public void verifyResultTextBox(SelenideElement resultLocator, String value){

        resultLocator.shouldHave(text(value));

    }

}

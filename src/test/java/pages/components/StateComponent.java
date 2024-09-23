package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateComponent {

    public void setState(String state){
        $("#stateCity-wrapper").$(byText(state)).click();
    }
}

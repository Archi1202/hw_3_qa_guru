package pages.components;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultTableComponent {



    public void checkResultOfRegistration(String key, String result)
    {
        $(".table-responsive").$(byTagAndText("td", key)).sibling(0).shouldHave(text(result));
    }

    public void checkNegativeResult()
    {
        $(".table-responsive").shouldNotBe(visible);
    }




}
package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {


    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillAllFieldsTest(){

        String firstName = "Anuar";
        String lastName = "Zh";
        String userEmail = "TestingTeam@qa.guru.com";
        String userNumber = "7777777777";
        String currentAddress = "Kazakhstan";
        String state = "Uttar Pradesh";
        String city = "Agra";

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);

        $("#genterWrapper")
                .$$("label").findBy(text("Male")).click();

        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select")
                    .selectOptionByValue("1994");
            $(".react-datepicker__month-select")
                    .selectOptionByValue("2");
            $$(".react-datepicker__day--010")
                .find(Condition.text("10")).click();

        $("#subjectsInput").setValue("English");
            $$(".subjects-auto-complete__option").find(Condition.text("English")).click();

        $("#hobbiesWrapper").
                $$("label").findBy(text("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("student_image.png");

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $("#react-select-3-input").setValue(state);


        $("#city").click();
        $("#react-select-4-input").setValue(city); // Select the city Agra from the list

        $("#submit").click(); // Click on the Submit button

        // Check that table is visible after clicking on Submit button
        $(".table-responsive").shouldBe(visible);

        //Verify that Final table has all required data, which student entered in the Form
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text("10 March,1994"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("student_image.png"));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text(state + " " + city));

        //Printing success message
        System.out.println("FillAllFieldsTest successfully passed!");
    }
}

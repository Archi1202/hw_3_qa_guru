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
    void FillAllFieldsTest(){

        //Including String variable for some of the fields in the Form

        String firstName = "Anuar";
        String lastName = "Zh";
        String userEmail = "TestingTeam@qa.guru.com";
        String userNumber = "7777777777";
        String currentAddress = "Kazakhstan";
        String state = "Uttar Pradesh";
        String city = "Agra";

        open("/automation-practice-form"); //open the Student Registration Form

        $("#firstName").setValue(firstName); // Insert name Anuar
        $("#lastName").setValue(lastName); // Insert LastName Zh
        $("#userEmail").setValue(userEmail); //Including student's email address

        $("#genterWrapper")
                .$$("label").findBy(text("Male")).click(); // find and click on the "Male" inside of labels in the Gender section

        $("#userNumber").setValue(userNumber); // insert phone number in the relevant section

        $("#dateOfBirthInput").click();         // Openning the DatePicker
            $(".react-datepicker__year-select")
                    .selectOptionByValue("1994"); // Setting the Year - 1994
            $(".react-datepicker__month-select")
                    .selectOptionByValue("2"); // Setting the month - March
        $$(".react-datepicker__day--010")
                .find(Condition.text("10")).click(); // Setting the Day - 10th

        $("#subjectsInput").setValue("English"); // Including the subject English
        $(".subjects-auto-complete__menu").shouldBe(visible); // Wait until the AutoComplete menu will be visible
            $$(".subjects-auto-complete__option").find(Condition.text("English")).click(); // Select "English from the AutoComplete list


        $("#hobbiesWrapper")
                .$$("label").findBy(text("Reading")).click(); // find and click on the "Reading" inside of labels in the Hobbies section

        $("#uploadPicture").uploadFromClasspath("student_image.png"); // Uploading picture from the resources folder inside the project

        $("#currentAddress").setValue(currentAddress); // inserting string value into the Current Address field

        $("#stateCity-wrapper #state").click();
                $$("div[id^='react-select-'][class*='option']")
                .findBy(text(state)).click(); // Selecting the State Uttar Pradesh


        $("#stateCity-wrapper #city").click();
                $$("div[id^='react-select-'][class*='option']")
                .findBy(text(city)).click(); // Selecting the city Agra

        $("#submit").click(); // Clicking on the Submit button

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

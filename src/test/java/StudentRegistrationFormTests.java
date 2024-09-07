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

        // String variables for some of the fields in the Form

        String firstName = "Anuar";
        String lastName = "Zh";
        String userEmail = "TestingTeam@qa.guru.com";
        String userNumber = "7777777777";
        String currentAddress = "Kazakhstan";
        String state = "Uttar Pradesh";
        String city = "Agra";

        open("/automation-practice-form"); //Open the Student Registration Form

        $("#firstName").setValue(firstName); // Insert name Anuar
        $("#lastName").setValue(lastName); // Insert LastName Zh
        $("#userEmail").setValue(userEmail); //Insert student's email address

        $("#genterWrapper")
                .$$("label").findBy(text("Male")).click(); // Find and click on the radio button near to the "Male" option inside of labels in the Gender section

        $("#userNumber").setValue(userNumber); // Insert phone number in the relevant section

        $("#dateOfBirthInput").click();         // Open the DatePicker
            $(".react-datepicker__year-select")
                    .selectOptionByValue("1994"); // Set the Year - 1994
            $(".react-datepicker__month-select")
                    .selectOptionByValue("2"); // Set the month - March
            $$(".react-datepicker__day--010")
                .find(Condition.text("10")).click(); // Set the Day - 10th

        $("#subjectsInput").setValue("English"); // Include the subject English into the field
            $$(".subjects-auto-complete__option").find(Condition.text("English")).click(); // Select "English from the AutoComplete list

        $("#hobbiesWrapper").
                $$("label").findBy(text("Reading")).click(); // Find and click on the "Reading" inside of labels in the Hobbies section

        $("#uploadPicture").uploadFromClasspath("student_image.png"); // Upload picture from the resources folder inside the project

        $("#currentAddress").setValue(currentAddress); // Insert string value into the Current Address field

        $("#stateCity-wrapper #state").click();
            $$("div[id^='react-select-'][class*='option']")
                    .findBy(text(state)).click(); // Select the State Uttar Pradesh from the list


        $("#stateCity-wrapper #city").click();
            $$("div[id^='react-select-'][class*='option']")
                    .findBy(text(city)).click(); // Select the city Agra from the list

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

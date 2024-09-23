package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentFormResolvedTests {


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void ValidationTestForm() {

        // String variables for some of the fields in the Form

        String firstName = "Anuar";
        String lastName = "Zh";
        String userEmail = "TestingTeam@qa.guru.com";
        String userNumber = "7777777777";
        String currentAddress = "Kazakhstan";
        String state = "Uttar Pradesh";
        String city = "Agra";

        open("/automation-practice-form");//Open the Student Registration Form
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));


        $("#firstName").setValue(firstName); // Insert name Anuar
        $("#lastName").setValue(lastName); // Insert LastName Zh
        $("#userEmail").setValue(userEmail); //Insert student's email address

//        $("#gender-radio-1").click(); //wrong option
//        $("#gender-radio-3").parent().click(); //option with going up to parent side
//        $(byText("Other")).click(); //by text option, only for English version of the web-site
        $("#genterWrapper").$(byText("Other")).click(); //by text option under the gender section
//        $("label[for=gender-radio-1]").click(); //by label option

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1994");
        $(".react-datepicker__month-select").selectOption("July");
//          $(".react-datepicker__day").selectOption();
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
//        $x("//*[@class='react-datepicker__day--030'][not(contains(@class, 'react-datepicker__day--outside-month'))]").click();

        $("#subjectsInput").setValue("English").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
//        $("#uploadPicture").uploadFile(new File("D:\\Automation with Java\\QA.GURU\\hw_3_qa_guru\\src\\test\\resources\\student_image.png") );

        $("#uploadPicture").uploadFromClasspath("student_image.png");
        $("#userNumber").setValue(userNumber);

        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
//        $("#react-select-3-option-0").click(); not clear which data we used
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").click(); // Click on the Submit button

//        $(".modal-dialog").shouldBe(Condition.visible);
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail), text(userNumber), text(currentAddress));



    }
}
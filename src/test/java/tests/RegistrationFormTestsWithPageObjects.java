package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationFormTestsWithPageObjects {


    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillAllFieldsTest(){

        String firstName = "Anuar";
        String lastName = "Zh";
        String gender = "Male";
        String userEmail = "TestingTeam@qa.guru.com";
        String userNumber = "7777777777";
        String subject = "Physics";
        String hobby = "Reading";
        String currentAddress = "Kazakhstan";
        String state = "Uttar Pradesh";
        String city = "Agra";
        String pathToPicture = "student_image.png";


        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth("10","March","1994")
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(pathToPicture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmitButton();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", "10" + " " + "March" + "," + "1994")
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", pathToPicture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);

        System.out.println("Complete All Fields Test successfully passed!");
    }

    @Test
    void fillOnlyRequiredFieldsTest(){

        String firstName = "AAA";
        String lastName = "BBB";
        String gender = "Other";
        String userEmail = "TestAnuar@gmail.com";
        String userNumber = "9999999999";


        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth("10","March","1994")
                .clickSubmitButton();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", "10" + " " + "March" + "," + "1994");

        System.out.println("Complete Only Required Fields Test successfully passed!");
    }
    @Test

    void incompleteFieldsTest(){

        String firstName = "AAA";
        String lastName = "BBB";
        String gender = "Other";


        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .clickSubmitButton();

        registrationPage.negativeVerificationOfResult();

        System.out.println("Result table is not displayed. Negative Test successfully passed!");
    }

}

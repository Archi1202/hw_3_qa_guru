package tests.hw11;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestBase;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

@Feature("Registration Form")
@Owner("Anuar Zhangeldi")
@DisplayName("Check the Registration Form via different scenarios by using Random data")

public class RegistrationRandomForJenkinsTest extends TestBase {

    RandomUtils random = new RandomUtils();

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("test1")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "DemoQA Registration Form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Completing All fields in the Form")
    void fillAllFieldsTest(){
        String firstName = random.setFakeFirstName();
        String lastName = random.setFakeLastName();
        String gender = random.setGender();
        String userEmail = random.setFakeEmail();
        String userNumber = random.setFakePhoneNumber();
        String subject = random.setFakeSubject();
        String hobby = random.setFakeHobby();
        String pathToPicture = random.getRandomPicture();
        String dayOfBirth = random.setDay();
        String monthOfBirth = random.setMonth();
        String yearOfBirth = random.setYear();

        String currentAddress = random.setFakeCurrentAddress();
        String state = random.setFakeState();
        String city = random.setFakeCity(state);
        step("Open the Registration Form Page",() -> {
            registrationPage.openPage()
                    .removeBanners();
        });
        step("Set Firstname, Lastname, Email, Gender and Number" ,() -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(userNumber);
        });
        step("Set Date of Birth in Calendar",() -> {
                registrationPage.setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth);
        });
        step("Include Subject and select it from the list",() -> {
            registrationPage.setSubject(subject);
        });
        step("Select Hobby from the available options",() -> {registrationPage.setHobby(hobby);
        });
        step("Upload Picture of the student", () -> {
                registrationPage.uploadPicture(pathToPicture);
        });
        step("Include information about current address",() -> {
            registrationPage.setCurrentAddress(currentAddress);
        });
        step("Select State and City from lists",() -> {
            registrationPage.removeBanners()
                    .setState(state)
                    .setCity(city);
        });
        step("Click on the Submit button",() -> {
            registrationPage.removeBanners()
                    .clickSubmitButton();
        });

        step("Check that included data for ALL Fields displayed in the result table", () -> {
            registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", pathToPicture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
        });
    }

    @Test
    @Tag("test1")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "DemoQA Registration Form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Filling ONLY REQUIRED fields in the Form")
    void fillOnlyRequiredFieldsTest(){

        String firstName = random.setFakeFirstName();
        String lastName = random.setFakeLastName();
        String gender = random.setGender();
        String userEmail = random.setFakeEmail();
        String userNumber = random.setFakePhoneNumber();
        String dayOfBirth = random.setDay();
        String monthOfBirth = random.setMonth();
        String yearOfBirth = random.setYear();

        step("Open the Registration Form Page",() -> {
            registrationPage.openPage()
                            .removeBanners();
        });
        step("Fill the Firstname, Lastname, Email, Gender and Number fields", () -> {
            registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber);
        });
        step("Select the Date of Birth from Calendar", () -> {
            registrationPage.setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth);
        });
        step("Click on the Submit button", () -> {
            registrationPage.removeBanners()
                    .clickSubmitButton();
        });

        step("Check the inserted data with result table for required fields only", () -> {
            registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        });
    }
    @Test
    @Tag("test1")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "DemoQA Registration Form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Negative Test to check the result table DOESN'T displayed for user")

    void incompleteFieldsTest(){

        String firstName = random.setFakeFirstName();
        String lastName = random.setFakeLastName();
        String gender = random.setGender();
        step("Open the Registration Form Page",() -> {
            registrationPage.openPage()
                    .removeBanners();
        });
        step("Insert Firstname, Lastname and Gender data",() -> {
            registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender);
        });
        step("Click on the Submit button", () -> {
            registrationPage.removeBanners()
                    .clickSubmitButton();
        });

        step("Check that there is no result table for incomplete form" ,() -> {registrationPage.negativeVerificationOfResult();
        });
    }

}

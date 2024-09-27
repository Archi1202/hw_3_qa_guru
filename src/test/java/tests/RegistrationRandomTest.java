package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

public class RegistrationRandomTest extends TestBase{

    RandomUtils random = new RandomUtils();

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
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
        registrationPage.openPage()
                        .removeBanners()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserEmail(userEmail)
                        .setGender(gender)
                        .setUserNumber(userNumber)
                        .setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth)
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
                        .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                        .checkResult("Subjects", subject)
                        .checkResult("Hobbies", hobby)
                        .checkResult("Picture", pathToPicture)
                        .checkResult("Address", currentAddress)
                        .checkResult("State and City", state + " " + city);
        System.out.println("Complete All Fields Test successfully passed!");
    }

    @Test
    void fillOnlyRequiredFieldsTest(){

        String firstName = random.setFakeFirstName();
        String lastName = random.setFakeLastName();
        String gender = random.setGender();
        String userEmail = random.setFakeEmail();
        String userNumber = random.setFakePhoneNumber();
        String dayOfBirth = random.setDay();
        String monthOfBirth = random.setMonth();
        String yearOfBirth = random.setYear();
        registrationPage.openPage()
                        .removeBanners()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserEmail(userEmail)
                        .setGender(gender)
                        .setUserNumber(userNumber)
                        .setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth)
                        .clickSubmitButton();
        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                        .checkResult("Student Email", userEmail)
                        .checkResult("Gender", gender)
                        .checkResult("Mobile", userNumber)
                        .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        System.out.println("Complete Only Required Fields Test successfully passed!");
    }
    @Test

    void incompleteFieldsTest(){

        String firstName = random.setFakeFirstName();
        String lastName = random.setFakeLastName();
        String gender = random.setGender();
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

package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput =$("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressCurrentInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitInput = $("#submit"),
            resultTableLocator = $(".table-responsive");

    CalendarComponent calendarComponent = new CalendarComponent();

    RegistrationResultTableComponent resultTableComponent = new RegistrationResultTableComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day,month,year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
            hobbiesInput.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String pathToPicture){
        pictureInput.uploadFromClasspath(pathToPicture);
        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress){
        addressCurrentInput.setValue(currentAddress);
        return this;

    }

    public RegistrationPage setState(String state) {
        stateInput.scrollIntoView(true).click();
        $("#stateCity-wrapper").$(byText(state)).scrollIntoView(true).click();
        return this;
    }

    public RegistrationPage setCity(String city){
        cityInput.click();
        $("#stateCity-wrapper").$(byText(city)).click();;
        return this;
    }

    public void clickSubmitButton(){
        submitInput.scrollIntoView(true).click();
    }

    public RegistrationPage checkResult(String key, String value) {
        resultTableComponent.checkResultOfRegistration(key, value);
        return this;
    }

    public void negativeVerificationOfResult(){
        resultTableComponent.checkNegativeResult();
    }
}
package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.CityComponent;
import pages.components.RegistrationResultTableComponent;
import pages.components.StateComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
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

    StateComponent stateComponent = new StateComponent();

    CityComponent cityComponent = new CityComponent();

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

    public RegistrationPage setState(String state){
        stateInput.click();
        stateComponent.setState(state);
        return this;
    }

    public RegistrationPage setCity(String city){
        cityInput.click();
        cityComponent.setCity(city);
        return this;
    }

    public void clickSubmitButton(){
        submitInput.click();
    }

    public RegistrationPage checkResult(String key, String value) {
        resultTableComponent.checkResultOfRegistration(key, value);
        return this;
    }

    public void negativeVerificationOfResult(){
        resultTableComponent.checkNegativeResult();
    }
}
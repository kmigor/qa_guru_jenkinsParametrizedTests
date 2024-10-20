package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTable;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement
            firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderInput = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirth = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesInput = $("#hobbiesWrapper");
    private final SelenideElement pictureInput = $("#uploadPicture");
    private final SelenideElement addressCurrentInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");
    private final SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    private final SelenideElement submitInput = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderInput.scrollTo().$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirth.scrollTo().click();
        new CalendarComponent().setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String[] values) {
        subjectsInput.scrollTo();
        for (String value : values) {
            subjectsInput.setValue(value).pressEnter();
        }
        return this;
    }

    public RegistrationPage setHobbies(String[] values) {
        hobbiesInput.scrollTo();
        for (String value : values) {
            hobbiesInput.$(byText(value)).click();
        }
        return this;
    }

    public RegistrationPage setPicture(String value) {
        if (Configuration.browser.equals("firefox")) return this;
        pictureInput.scrollTo().uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        addressCurrentInput.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setStateAndCity(String[] stateAndCityArray) {
        stateInput.scrollTo().click();
        stateCityWrapper.$(byText(stateAndCityArray[0])).click();
        cityInput.scrollTo().click();
        stateCityWrapper.$(byText(stateAndCityArray[1])).click();
        return this;
    }


    public void clickSubmit() {
        submitInput.scrollTo().click();
    }

    public RegistrationPage checkResult(String key, String value) {
        new ResultTable().checkResult(key, value);
        return this;
    }

    public void negativeCheck() {
        new ResultTable().negativeCheck();
    }

}
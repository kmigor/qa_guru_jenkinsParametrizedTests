package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomDataGenerator;


public class AutomationPracticeFormWithPageObjectTests extends TestBase {

    private final RegistrationPage registrationPage = new RegistrationPage();
    private final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
    String[] genders = {"Male", "Female", "Other"};
    String[] allSubjects = {
            "Accounting",
            "Arts",
            "Biology",
            "Chemistry",
            "Civics",
            "Commerce",
            "Computer Science",
            "Economics",
            "English",
            "Hindi",
            "History",
            "Maths",
            "Physics",
            "Social Studies"
    };
    String[] allHobbies = {"Sports", "Reading", "Music"};
    String[] allPictures = {"rick1.jpg", "rick2.jpg", "rick3.jpg"};
    String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    String[][] cities = {
            {"Delhi", "Gurgaon", "Noida"},
            {"Agra", "Lucknow", "Merrut"},
            {"Karnal", "Panipat"},
            {"Jaipur", "Jaiselmer"}
    };

    @Test
    void successfulRegistrationWithFullDataTest() {

        String firstName = randomDataGenerator.getRandomFirstName();
        String lastName = randomDataGenerator.getRandomLastName();
        String userEmail = randomDataGenerator.getRandomEmail();
        String gender = randomDataGenerator.getRandomGender(genders);
        String phoneNumber = randomDataGenerator.getRandomPhoneNumber();
        String dayOfBirth = randomDataGenerator.getRandomDay();
        String monthOfBirth = randomDataGenerator.getRandomMonth();
        String yearOfBirth = randomDataGenerator.getRandomYear();
        String[] subjects = randomDataGenerator.getRandomSubjects(allSubjects);
        String[] hobbies = randomDataGenerator.getRandomHobbies(allHobbies);
        String pictureName = randomDataGenerator.getRandomPicture(allPictures);
        String currentAddress = randomDataGenerator.getRandomAddress();
        String[] stateAndCity = randomDataGenerator.getRandomStateAndCity(states, cities);

        registrationPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setPicture(pictureName)
                .setCurrentAddress(currentAddress)
                .setStateAndCity(stateAndCity)
                .clickSubmit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth",dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects[0])
                .checkResult("Hobbies", hobbies[0])
                .checkResult("Picture", pictureName)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", stateAndCity[0] + " " + stateAndCity[1]);
    }

    @Test
    void successfulRegistrationWithRequiredFieldsTest() {
        String firstName = randomDataGenerator.getRandomFirstName();
        String lastName = randomDataGenerator.getRandomLastName();
        String gender = randomDataGenerator.getRandomGender(genders);;
        String phoneNumber = randomDataGenerator.getRandomPhoneNumber();

        registrationPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .clickSubmit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
    }

    @Test
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .clickSubmit();

        registrationPage.negativeCheck();
    }
}
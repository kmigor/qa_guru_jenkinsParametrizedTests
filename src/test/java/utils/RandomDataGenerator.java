package utils;

import com.github.javafaker.Faker;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

public class RandomDataGenerator {

    Faker faker = new Faker(new Locale("en-US"));

    Date dateOfBirth = faker.date().between(new Date(0, Calendar.JANUARY,1),new Date());

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }
    public String getRandomGender(String[] genders) {
        return genders[faker.number().numberBetween(0, genders.length - 1)];
    }

    public String getRandomPhoneNumber() {
        return faker.number().digits(10);
    }

    public String getRandomYear() {
        return String.valueOf(dateOfBirth.getYear() + 1900);
    }

    public String getRandomMonth() {
        Month month = Month.of(dateOfBirth.getMonth());
        return month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public String getRandomDay() {
        return String.format("%02d", dateOfBirth.getDate());
    }

    public String[] getRandomSubjects(String[] allSubjects) {

        String[] randomSubjects = new String[faker.number().numberBetween(1, allSubjects.length)];

        for (int i = 0; i < randomSubjects.length; i++) {
            randomSubjects[i] = faker.options().option(allSubjects);
            allSubjects = removeElementFromArray(randomSubjects[i], allSubjects);
        }

        return randomSubjects;

    }

    public String[] getRandomHobbies(String[] allHobbies) {

        String[] randomHobbies = new String[faker.number().numberBetween(1, allHobbies.length)];

        for (int i = 0; i < randomHobbies.length; i++) {
            randomHobbies[i] = faker.options().option(allHobbies);
            allHobbies = removeElementFromArray(randomHobbies[i], allHobbies);
        }

        return randomHobbies;
    }

    public String getRandomPicture(String[] pictures) {

        return faker.options().option(pictures);

    }

    public String getRandomAddress() {

        return String.valueOf(faker.address().streetAddress());

    }

    public String[] getRandomStateAndCity(String[] states, String[][] cities) {
        int stateNumber = faker.number().numberBetween(0, 3);
        int cityNumber = faker.number().numberBetween(0, cities[stateNumber].length);
        String[] randomStateAndCity = new String[2];
        randomStateAndCity[0] = states[stateNumber];
        randomStateAndCity[1] = cities[stateNumber][cityNumber];
        return randomStateAndCity;
    }

    private String[] removeElementFromArray(String element, String[] array) {

        int newLength = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != element) {
                newLength++;
            }
        }
        String[] resultArray = new String[newLength];
        newLength = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != element) {
                resultArray[newLength++] = array[i];
            }
        }

        return resultArray;
    }

}
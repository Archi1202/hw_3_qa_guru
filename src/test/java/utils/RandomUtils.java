package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {

    Faker faker = new Faker(new Locale("en-GB"));



    public String setFakeFirstName(){
        String name = faker.name().firstName();
        return name;
    }

    public String setFakeLastName(){
        String lastName = faker.name().lastName();
        return lastName;
    }

    public String setGender() {
        String[] gender = {"Male", "Female", "Other"};
        String selectedGender = faker.options().option(gender);
        return selectedGender;
    }

    public String setFakeEmail(){
        String emailAddress = faker.internet().emailAddress();
        return emailAddress;
    }

    public String setFakePhoneNumber(){
        String phoneNumber = faker.number().digits(10);
        return phoneNumber;
    }
    public String setYear(){
        int selectedYear = faker.number().numberBetween(1900,2100);
        return String.valueOf(selectedYear) ;
    }
    public String setMonth() {
        String[] months = {"January", "February", "March", "April", "June", "July", "August", "September", "October", "November", "December"};
        String selectedMonth = faker.options().option(months);
        return selectedMonth;
    }

    public String setDay() {
        return String.format("%02d",faker.number().numberBetween(1,28));
    }

    public String setFakeSubject(){
        String[] subject = {"History", "Civics", "Biology","English","Computer Science"};
        String selectedSubject = faker.options().option(subject);
        return selectedSubject;
    }

    public String setFakeHobby(){
        String[] hobby = {"Reading", "Sports", "Music"};
        String selectedHobby = faker.options().option(hobby);
        return selectedHobby;
    }

    public String getRandomPicture() {
        String[] files = {"student_image.png", "bugs.jpg", "cat_dev.jpg"};
        String selectedFile = faker.options().option(files);
        return selectedFile;
    }

    public String setFakeCurrentAddress(){
        String currentAddress = faker.address().fullAddress();
        return currentAddress;
    }

    public String setFakeState(){
        String[] state = {"NCR", "Uttar Pradesh", "Haryana","Rajasthan"};
        String selectedState = faker.options().option(state);
        return selectedState;
    }

    public String setFakeCity(String selectedState) {
        if ("NCR".equals(selectedState)) {
            return faker.options().option("Delhi", "Gurgaon", "Noida");
        } else if ("Uttar Pradesh".equals(selectedState)) {
            return faker.options().option("Agra", "Lucknow", "Merrut");
        } else if ("Haryana".equals(selectedState)) {
            return faker.options().option("Karnal", "Panipat");
        } else if ("Rajasthan".equals(selectedState)) {
            return faker.options().option("Jaipur", "Jaiselmer");
        } else {
            return ("Invalid state: " + selectedState);
        }
    }
}

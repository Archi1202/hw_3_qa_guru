package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.TextBoxPage;

public class TextBoxTests extends TextBoxPage {

    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillAllFieldsInTextBoxTest(){

        String fullName = "Alexander Nubel";
        String userEmail = "Nubel.Bayern@gmail.com";
        String cuAddress = "Siegenburger Straße 45, 81373 Munich";
        String peAddress = "Verein für Bewegungsspiele Stuttgart 1893";


        textBoxPage.openPage()
                .removeBanners()
                .setUserName(fullName)
                .setUserEmail(userEmail)
                .setCurrentAddress(cuAddress)
                .setPermanentAddress(peAddress)
                .clickSubmit();

        textBoxPage.checkTheTextBoxResult(fullName)
                .checkTheTextBoxResult(userEmail)
                .checkTheTextBoxResult(cuAddress)
                .checkTheTextBoxResult(peAddress);
    }

    @Test
    void fillOnlyNameInTextBoxTest(){

        String fullName = "Alexander";


        textBoxPage.openPage()
                .removeBanners()
                .setUserName(fullName)
                .clickSubmit();

        textBoxPage.checkTheTextBoxResult(fullName);
    }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectAndEditContact();
        app.getContactHelper().fillContactForm(new ContactData(
                "Petr",
                "Petrovich",
                "Petrov",
                "Real Nickname",
                "Real Title",
                "Real Company",
                "Real Address 1",
                "Moscow",
                "81234567890",
                "Real Work",
                "Real Fax",
                "Real1@Real.com",
                "Real2@Real.com",
                "Real3@Real.com",
                "Real Homepage",
                "Real Address 2",
                "Real Home",
                "Real Note"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }
}

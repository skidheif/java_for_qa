package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                            "Ivan",
                            "Ivanovich",
                            "Ivanov",
                            "Test Nickname",
                            "Test Title",
                            "Test Company",
                            "Test Address 1",
                            "Moscow",
                            "81234567890",
                            "Test Work",
                            "Test Fax",
                            "test1@test.com",
                            "test2@test.com",
                            "test3@test.com",
                            "Test Homepage",
                            "Test Address 2",
                            "Test Home",
                            "Test Note",
                            "test1"),
                    true);
        }
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
                        "Real Note",
                        null),
                false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }
}

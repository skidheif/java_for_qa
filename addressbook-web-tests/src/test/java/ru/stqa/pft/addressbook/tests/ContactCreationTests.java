package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        app.getNavigationHelper().gotoAddNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData(
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
                "Test Note"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomepage();
    }
}
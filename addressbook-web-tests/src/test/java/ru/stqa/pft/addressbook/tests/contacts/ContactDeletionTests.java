package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitDeleteForm();
        app.getNavigationHelper().gotoHomePage();
    }
}

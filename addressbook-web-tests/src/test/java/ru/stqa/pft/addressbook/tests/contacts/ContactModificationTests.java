package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        app.goTo().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                            "Ivan",
                            "Ivanov",
                            "Test Nickname",
                            "Test Title",
                            "Test company",
                            "Moscow",
                            "81234567890",
                            "test@test.com",
                            "test1"),
                    true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectAndEditContact(before.size() - 1);
        ContactData contact = new ContactData(
                "Petr",
                "Petrov",
                "Petrov Nickname",
                "Petrov Title",
                "Petrov company",
                "Petrov",
                "89997778800",
                "petrov@petrov.com",
                null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }
}

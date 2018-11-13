package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroupFromList()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getContactHelper().returnToHomepage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(
                "Ivan",
                "Ivanov",
                "Test Nickname",
                "Test Title",
                "Test company",
                "Moscow",
                "81234567890",
                "test@test.com",
                "test1");
        app.getContactHelper().createContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

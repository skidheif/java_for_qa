package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contacts.ContactData;
import ru.stqa.pft.addressbook.model.contacts.Contacts;
import ru.stqa.pft.addressbook.model.groups.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                            .withName("Ivan")
                            .withLastName("Ivanov")
                            .withNickname("Test Nickname")
                            .withTitle("Test Title")
                            .withCompany("Test company")
                            .withAddress("Moscow")
                            .withMobilePhone("81234567890")
                            .withEmail("test@test.com")
                            .inGroup(groups.iterator().next()),
                    true);
        }
    }

    @Test
    public void testContactModification() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withName("Petr")
                .withLastName("Petrov")
                .withNickname("Petrov Nickname")
                .withTitle("Petrov Title")
                .withCompany("Petrov company")
                .withAddress("Petrov")
                .withMobilePhone("89997778800")
                .withEmail("petrov@petrov.com")
                .inGroup(groups.iterator().next());
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}

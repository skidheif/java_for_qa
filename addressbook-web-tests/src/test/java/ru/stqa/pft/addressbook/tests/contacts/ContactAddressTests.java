package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contacts.ContactData;
import ru.stqa.pft.addressbook.model.groups.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                            .withName("Ivan")
                            .withLastName("Ivanov")
                            .withNickname("Test Nickname")
                            .withTitle("Test Title")
                            .withCompany("Test company")
                            .withAddress("Moscow" + "\n" + "Omsk")
                            .withMobilePhone("81234567890")
                            .withEmail("test@test.com")
                            .inGroup(groups.iterator().next()),
                    true);
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}

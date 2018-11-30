package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contacts.ContactData;
import ru.stqa.pft.addressbook.model.groups.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests extends TestBase {

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
                            .withEmail("test1@test.com" + "\n" + "test2@test.com" + "\n" + "test3@test.com")
                            .inGroup(groups.iterator().next()),
                    true);
        }
    }

    @Test
    public void testContactEmail(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream()
                .filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}

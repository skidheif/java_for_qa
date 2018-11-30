package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contacts.ContactData;
import ru.stqa.pft.addressbook.model.contacts.Contacts;
import ru.stqa.pft.addressbook.model.groups.GroupData;
import ru.stqa.pft.addressbook.model.groups.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
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
    public void testContactRemoveFromGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        Random rand = new Random();
        ContactData selectedContact = contacts.stream().skip(rand.nextInt(contacts.size()) % contacts.size()).findFirst().get();
        Groups selectedContactGroupsBefore = selectedContact.getGroups();
        GroupData deletingGroup;
        app.goTo().homePage();
        if (selectedContactGroupsBefore.size() > 0) {
            deletingGroup = selectedContactGroupsBefore.iterator().next();
            app.contact().removeFromGroup(selectedContact, deletingGroup);
        } else {
            deletingGroup = groups.iterator().next();
            app.contact().addToGroup(selectedContact, deletingGroup);
            selectedContactGroupsBefore = app.db().contactById(selectedContact.getId()).iterator().next().getGroups();
            app.contact().removeFromGroup(selectedContact, deletingGroup);
        }
        Groups selectedContactGroupsAfter = app.db().contactById(selectedContact.getId()).iterator().next().getGroups();
        assertThat(selectedContactGroupsAfter, equalTo(selectedContactGroupsBefore.withOut(deletingGroup)));
    }
}

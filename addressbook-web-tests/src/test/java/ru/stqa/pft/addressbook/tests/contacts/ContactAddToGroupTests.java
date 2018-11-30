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

public class ContactAddToGroupTests extends TestBase {

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
    public void testContactAddToGroup() {
        Groups groupsBefore = app.db().groups();
        Contacts contactsBefore = app.db().contacts();
        Random rand = new Random();
        ContactData selectedContact = contactsBefore.stream().skip(rand.nextInt(contactsBefore.size()) % contactsBefore.size()).findFirst().get();
        Groups selectedContactGroupsBefore = selectedContact.getGroups();
        groupsBefore.removeAll(selectedContactGroupsBefore);
        GroupData addedGroup;
        app.goTo().homePage();
        if (groupsBefore.size() > 0) {
            addedGroup = groupsBefore.iterator().next();
            app.contact().addToGroup(selectedContact, addedGroup);
        } else {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewTestGroup"));
            Groups groupsAfter = app.db().groups();
            groupsAfter.removeAll(selectedContactGroupsBefore);
            addedGroup = groupsAfter.iterator().next();
            app.goTo().homePage();
            app.contact().addToGroup(selectedContact, addedGroup);
        }
        Groups selectedContactGroupsAfter = app.db().contactById(selectedContact.getId()).iterator().next().getGroups();
        assertThat(selectedContactGroupsAfter, equalTo(selectedContactGroupsBefore.withAdded(addedGroup)));
    }
}

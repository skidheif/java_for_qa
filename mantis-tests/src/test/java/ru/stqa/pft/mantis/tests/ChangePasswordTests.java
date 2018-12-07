package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserMantis;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        String oldPass = "password";
        String newPass = String.format(oldPass + "%s", now);
        Users allUsers = app.db().users();
        UserMantis selectedUser = allUsers.iterator().next();
        String selectedUserName = selectedUser.getUserName();
        String selectedUserEmail = selectedUser.getEmail();

        app.james().drainEmail(selectedUserName, oldPass);
        app.goTo().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().manageUsers();
        app.user().resetPassword(selectedUser);

        List<MailMessage> mailMessages = app.james().waitForMail(selectedUserName, oldPass, 40000);
        String changePasswordLink = findChangePasswordLink(mailMessages, selectedUserEmail);
        app.registration().finish(changePasswordLink, newPass);
        HttpSession session = app.newSession();
        assertTrue(session.login(selectedUserName, newPass));
        assertTrue(session.isLoggedInAs(selectedUserName));
    }

    private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
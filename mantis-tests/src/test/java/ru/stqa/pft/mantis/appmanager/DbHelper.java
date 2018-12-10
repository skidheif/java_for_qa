package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.UserMantis;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DbHelper {
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
    }

    public Users users() {
        Connection connection = null;
        Users result = new Users();
        try {
            connection = DriverManager.getConnection(app.getProperty("db.url"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, username, email from mantis_user_table where id<>1");
            while (resultSet.next()) {
                result.add(new UserMantis()
                        .withId(resultSet.getInt("id"))
                        .withUserName(resultSet.getString("username"))
                        .withEmail(resultSet.getString("email")));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return new Users(result);
    }

    public Set<Issue> issuesId() {
        Connection connection = null;
        Set<Issue> result = new HashSet<Issue>();
        try {
            connection = DriverManager.getConnection(app.getProperty("db.url"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from mantis_bug_table");
            while (resultSet.next()) {
                result.add(new Issue()
                        .withId(resultSet.getInt("id")));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return new HashSet<Issue>(result);
    }
}
package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String name;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(int id, String name, String lastName, String nickname, String title, String company, String address, String mobile, String email, String group) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactData(String name, String lastName, String nickname, String title, String company, String address, String mobile, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }
}

package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company_title;
    private final String address1;
    private final String home1;
    private final String mobile;
    private final String work;
    private final String fax;
    private final String email1;
    private final String email2;
    private final String email3;
    private final String homepage;
    private final String address2;
    private final String home2;
    private final String note;
    private String group;

    public ContactData(String name,
                       String middleName,
                       String lastName,
                       String nickname,
                       String title,
                       String company_title,
                       String address1,
                       String home1,
                       String mobile,
                       String work,
                       String fax,
                       String email1,
                       String email2,
                       String email3,
                       String homepage,
                       String address2,
                       String home2,
                       String note,
                       String group) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company_title = company_title;
        this.address1 = address1;
        this.home1 = home1;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.address2 = address2;
        this.home2 = home2;
        this.note = note;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
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

    public String getCompany_title() {
        return company_title;
    }

    public String getAddress1() {
        return address1;
    }

    public String getHome1() {
        return home1;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHome2() {
        return home2;
    }

    public String getNote() {
        return note;
    }

    public String getGroup() {
        return group;
    }
}

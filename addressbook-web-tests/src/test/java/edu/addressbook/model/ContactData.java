package edu.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String lastname;
    private String firstname;
    private String email;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactData)) return false;
        ContactData that = (ContactData) o;
        return getId() == that.getId() && Objects.equals(getLastname(), that.getLastname()) && Objects.equals(getFirstname(), that.getFirstname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastname(), getFirstname());
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

}
package org.lentils.oboronataganroga1855.model;

public class User {

    private String fullName;
    private String passport;
    private String email;

    public User(String fullName, String passport, String email){
        this.fullName = fullName;
        this.passport = passport;
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPassport() {
        return this.passport;
    }

    public String getEmail() {
        return this.email;
    }
}

package com.ecomshop.deskplus.web.rest;

/**
 * Author: Sheik Syed Ali
 * Date: 26 Sep 2021
 */
public class SignupRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String stripePlanId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStripePlanId() {
        return stripePlanId;
    }

    public void setStripePlanId(String stripePlanId) {
        this.stripePlanId = stripePlanId;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", stripePlanId='" + stripePlanId + '\'' +
                '}';
    }
}

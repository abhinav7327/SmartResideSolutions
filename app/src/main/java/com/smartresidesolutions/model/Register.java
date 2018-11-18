package com.smartresidesolutions.model;


import java.io.Serializable;

public class Register implements Serializable {
    String firstName;
    String lastName;
    String email;
    String mobile;
    String birthdate;
    String kycNumber;
    String kycType;
    String gender;

    public Register(String firstName,String lastName, String email, String mobile, String birthdate, String kycNumber, String kycType, String gender) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.email = email;
        this.mobile = mobile;
        this.birthdate = birthdate;
        this.kycNumber = kycNumber;
        this.kycNumber = kycNumber;
        this.kycType = kycType;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getKycNumber() {
        return kycNumber;
    }

    public void setKycNumber(String kycNumber) {
        this.kycNumber = kycNumber;
    }

    public String getKycType() {
        return kycType;
    }

    public void setKycType(String kycType) {
        this.kycType = kycType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

package com.example.withscenebuilder;

import java.time.LocalDate;

public class Customer {

    private int C_id;
    private String Cname;
    private String Gender;
    private String Email;
    private int Phone_number;
    private LocalDate Birthdate;

    public Customer() {

    }

    public Customer(int c_id, String cname, String gender, String email, int phone_number, LocalDate birthdate) {
        super();
        C_id = c_id;
        Cname = cname;
        Gender = gender;
        Email = email;
        Phone_number = phone_number;
        Birthdate = birthdate;
    }

    public Customer(String cname, String gender, String email, int phone_number, LocalDate birthdate) {
        super();
        Cname = cname;
        Gender = gender;
        Email = email;
        Phone_number = phone_number;
        Birthdate = birthdate;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(int phone_number) {
        Phone_number = phone_number;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        Birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Customer [C_id=" + C_id + ", Cname=" + Cname + ", Gender=" + Gender + ", Email=" + Email
                + ", Phone_number=" + Phone_number + ", Birthdate=" + Birthdate + "]";
    }
}

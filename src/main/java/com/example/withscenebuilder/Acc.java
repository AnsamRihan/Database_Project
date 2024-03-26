package com.example.withscenebuilder;

import java.util.Date;

public class Acc {

    private String Username;
    private String pass;
    private Date Start_date;
    private int C_id;

    public Acc() {

    }

    public Acc(String username, String pass, Date start_date, int c_id) {
        super();
        Username = username;
        this.pass = pass;
        Start_date = start_date;
        C_id = c_id;
    }

    public Acc(String username, String pass) {
        super();
        Username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public void setStart_date(Date start_date) {
        Start_date = start_date;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    @Override
    public String toString() {
        return "Acc [Username=" + Username + ", pass=" + pass + ", Start_date=" + Start_date + ", C_id=" + C_id + "]";
    }
}

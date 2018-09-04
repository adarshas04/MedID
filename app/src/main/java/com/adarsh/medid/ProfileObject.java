package com.adarsh.medid;

public class ProfileObject {

    String fname;
    String lname;
    String mob;
    String dob;
    String email;

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMob() {
        return mob;
    }

    public String getDob() {
        return dob;
    }

    public ProfileObject(String fname, String lname, String mob, String dob, String email) {

        this.fname = fname;
        this.lname = lname;
        this.mob = mob;
        this.dob = dob;
        this.email = email;

    }
}

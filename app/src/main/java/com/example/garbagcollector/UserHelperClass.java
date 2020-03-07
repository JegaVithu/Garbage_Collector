package com.example.garbagcollector;

public class UserHelperClass {

    String fname, lname, phonum, address, email, password, type, logn, latit, username;

    public UserHelperClass() {

    }

    public UserHelperClass(String fname, String lname, String phonum, String address, String email, String password, String type, String logn, String latit, String username) {
        this.fname = fname;
        this.lname = lname;
        this.phonum = phonum;
        this.address = address;
        this.email = email;
        this.password = password;
        this.type = type;
        this.logn = logn;
        this.latit = latit;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhonum() {
        return phonum;
    }

    public void setPhonum(String phonum) {
        this.phonum = phonum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogn() {
        return logn;
    }

    public void setLogn(String logn) {
        this.logn = logn;
    }

    public String getLatit() {
        return latit;
    }

    public void setLatit(String latit) {
        this.latit = latit;
    }
}

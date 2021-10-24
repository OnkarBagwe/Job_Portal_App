package com.example.jobportalapp.Model;

public class ProfileData {

    String full_name;
    String phone_no;
    String designation;
    String email;
    String password;

    String user_id;
    String join_date;

    public ProfileData(){

    }

    public ProfileData(String full_name, String phone_no, String designation, String email, String password, String user_id, String join_date) {
        this.full_name = full_name;
        this.phone_no = phone_no;
        this.designation = designation;
        this.email = email;
        this.password = password;
        this.user_id = user_id;
        this.join_date = join_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }
}

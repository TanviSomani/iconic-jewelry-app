package com.example.iconicjewelry;

public class UserModel {

    public String firstName = "";
    public String lastName = "";
    public String email = "";
    public String aptNo = "";
    public String buildingNo = "";
    public String streetName = "";
    public String city = "";
    public String province = "";
    public String postalCode = "";

    public UserModel(String firstName, String lastName, String email, String aptNo, String buildingNo, String streetName, String city, String province, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.aptNo = aptNo;
        this.buildingNo = buildingNo;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }
}

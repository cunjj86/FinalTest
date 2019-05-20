package com.tje.finaltest.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MyProfile implements Serializable {

    public String name;
    public String logo;
    public String phoneNumber;
    public String email;
    public String bankName;
    public String billingAcc;

    public MyProfile(String name, String logo, String phoneNumber, String email, String bankName, String billingAcc) {
        this.name = name;
        this.logo = logo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bankName = bankName;
        this.billingAcc = billingAcc;
    }
}

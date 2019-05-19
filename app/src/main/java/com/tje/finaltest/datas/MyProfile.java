package com.tje.finaltest.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class MyProfile {

    public String code;
    public String name;
    public String logo;
    public String phoneNumber;
    public String bankName;
    public String billingAcc;

    public static MyProfile getProFileFromJson(JSONObject profileJson) {

        MyProfile profile = new MyProfile();

        try {
            profile.code = profileJson.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return profile;
    }
}

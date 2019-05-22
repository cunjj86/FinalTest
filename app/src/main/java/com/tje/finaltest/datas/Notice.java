package com.tje.finaltest.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Notice implements Serializable {

    public int id;
    public String inputDtm;
    public String title;
    public String content;

    public static Notice getNoticeFromJson(JSONObject noticeJson) {

        Notice noticeObject = new Notice();

        try {
            noticeObject.id = noticeJson.getInt("id");
            noticeObject.inputDtm = noticeJson.getString("created_at");
            noticeObject.title = noticeJson.getString("title");
            noticeObject.content = noticeJson.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noticeObject;

    }
}

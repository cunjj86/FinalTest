package com.tje.finaltest.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

//    어떤 파일 (메모장 : ~~.txt)에 저장할지 경로를 담아두는 변수
    private static final String prefName = "finalTestPref";

//    이 메모에서 다루는 항목들의 리스트를 변수로 생성.
    private static final String USER_TOKEN = "USER_TOKEN";

//    실제로 각각의 항목을 저장/불러오는 기능

//    setter : 토큰값을 저장하는 token_setter

    public static void setUserToken(Context context, String token) {

//        메모장 파일 (TXT) 를 여는 작업.

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

//        실제로 데이터 저장하기

        pref.edit().putString(USER_TOKEN, token).apply();

    }

//    getter : 저장된 토큰값이 있다면, 불러오기.

    public static String getUserToken(Context context) {

//        메모장 열어주는 작업.

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        return pref.getString(USER_TOKEN,"");

    }

}

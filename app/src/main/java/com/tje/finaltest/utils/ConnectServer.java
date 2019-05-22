package com.tje.finaltest.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectServer {

    //    서버의 근본 주소. => 프로젝트마다 서버를 확인하고 주소를 변경
    private final static String BASE_URL = "http://delivery-dev-389146667.ap-northeast-2.elb.amazonaws.com";

    //    서버의 응답을 처리하는 역할을 담당하는 인터페이스
    public interface JsonResponseHandler {

        void onResponse(JSONObject json);
    }

    public static void postRequestSignIn(Context context, String userid, String password, final JsonResponseHandler handler) {

//        클라이언트 역할은 무슨 메소드이든 동일. 항상 복붙

        OkHttpClient client = new OkHttpClient();

//        POST 메소드는 urlBuilder가 아니라, RequestBody를 Build
//        formData에 파라미터를 첨부하는 코드

        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", userid)
                .add("password", password)
                .build();

//        실제 Request를 생성, 서버로 떠날 준비.

        Request request = new Request.Builder()
                .url(BASE_URL+"/auth")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseContent = response.body().string();

                Log.d("서버 응답 내용", responseContent);

                try {
//                    받아온 응답을 JSON 객체로 변환
                    JSONObject json = new JSONObject(responseContent);

                    if (handler != null) {
//                        화면에서 처리하는 코드가 있으면 실행시켜줌.
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void getRequestMyInfo(Context context, String token,final JsonResponseHandler handler) {

//        서버 - 클라이언트 (앱)

        OkHttpClient client = new OkHttpClient();

//        URL 설정 => 목적지 설정

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/v2/me_info").newBuilder();

//        * GET, DELETE 메소드는 필요 파라미터를 URL에 담아줘야함.
//         이 담는 과정을 쉽게 하려고 urlBuilder를 사용

//        실제로 서버에 접근하는 완성된 URL
        String requestURL = urlBuilder.build().toString();

//        완성된 URL로 접근하는 request를 생성.
        Request request = new Request.Builder()
                .header("X-Http-Token", token)
                .url(requestURL) // post 등의 메소드를 안쓰면, 기본적으로 GET 방식
                .build();

//        만들어진 request를 실제로 서버에 요청.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseContent = response.body().string();

                Log.d("서버 응답 내용", responseContent);

                try {
//                    받아온 응답을 JSON  객체로 변환
                    JSONObject json = new JSONObject(responseContent);

                    if (handler != null) {
//                        화면에서 처리하는 코드가 있으면 실행시켜줌
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void getRequestNoitice(Context context, String token,final JsonResponseHandler handler) {

//        서버 - 클라이언트 (앱)

        OkHttpClient client = new OkHttpClient();

//        URL 설정 => 목적지 설정

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/announcement").newBuilder();

//        * GET, DELETE 메소드는 필요 파라미터를 URL에 담아줘야함.
//         이 담는 과정을 쉽게 하려고 urlBuilder를 사용

//        실제로 서버에 접근하는 완성된 URL
        String requestURL = urlBuilder.build().toString();

//        완성된 URL로 접근하는 request를 생성.
        Request request = new Request.Builder()
                .header("X-Http-Token", token)
                .url(requestURL) // post 등의 메소드를 안쓰면, 기본적으로 GET 방식
                .build();

//        만들어진 request를 실제로 서버에 요청.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseContent = response.body().string();

                Log.d("서버 응답 내용", responseContent);

                try {
//                    받아온 응답을 JSON  객체로 변환
                    JSONObject json = new JSONObject(responseContent);

                    if (handler != null) {
//                        화면에서 처리하는 코드가 있으면 실행시켜줌
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

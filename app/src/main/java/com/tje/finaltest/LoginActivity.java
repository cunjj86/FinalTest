package com.tje.finaltest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tje.finaltest.databinding.ActivityLoginBinding;
import com.tje.finaltest.utils.ConnectServer;
import com.tje.finaltest.utils.ContextUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        act.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idEdt = act.idEdt.getText().toString();
                String pwEdt = act.pwEdt.getText().toString();

                ConnectServer.postRequestSignIn(mContext, idEdt, pwEdt, new ConnectServer.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int code = json.getInt("code");

                                    if (code == 200) {
                                        JSONObject data = json.getJSONObject("data");
                                        String token = data.getString("token");

//                                SharedPreference 에 token 을 저장

                                        ContextUtil.setUserToken(mContext, token);

                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.putExtra("userToken", token);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(mContext, "로그인에 실패하였습니다!", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });

            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }
}

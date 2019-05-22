package com.tje.finaltest;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tje.finaltest.adapters.MainViewPagerAdapter;
import com.tje.finaltest.adapters.NoticeAdapter;
import com.tje.finaltest.databinding.ActivityMainBinding;
import com.tje.finaltest.datas.MyProfile;
import com.tje.finaltest.datas.Notice;
import com.tje.finaltest.fragments.MyProfileFragment;
import com.tje.finaltest.fragments.NoticeFragment;
import com.tje.finaltest.utils.ConnectServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

//    제약사항 1. 탭 레이아웃 사용
//    2. 각각의 앱 내용은 Fragment 활용
//    3. 뷰페이저를 활용해 생성된 프래그먼트 2개를 페이징 처리

    ActivityMainBinding act;

    MainViewPagerAdapter mvpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        act.tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(act.viewPager));
        act.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(act.tabLayout));

    }

    @Override
    public void setValues() {

        act.tabLayout.addTab(act.tabLayout.newTab().setText("내 프로필"));
        act.tabLayout.addTab(act.tabLayout.newTab().setText("공지사항"));

        mvpa = new MainViewPagerAdapter(getSupportFragmentManager(), 2);
        act.viewPager.setAdapter(mvpa);

        Fragment currentFlag = mvpa.getItem(act.viewPager.getCurrentItem());
        String token = getIntent().getStringExtra("userToken");

        setMyProfile(currentFlag, token);

    }

    void setMyProfile(Fragment cFlag, String token) {
        ConnectServer.getRequestMyInfo(mContext, token, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int code = 0;
                        try {
                            code = json.getInt("code");

                            if (code == 200) {

                                JSONObject data = json.getJSONObject("data");
                                JSONObject user = data.getJSONObject("user");

                                String logo = user.getString("profile_image");
                                String name = user.getString("name");
                                String phone = user.getString("phone");
                                String email = user.getString("email");
                                String billing = user.getString("billing_account");

                                JSONObject bank = user.getJSONObject("bank_code");
                                String bankName = bank.getString("name");

                                MyProfile myProfile = new MyProfile(name, logo, phone, email, bankName, billing);

                                Log.d("이름:",myProfile.name);

                                ((MyProfileFragment) cFlag).setContent(myProfile);
                            }
                            else {

                                try {
                                    String message = json.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}

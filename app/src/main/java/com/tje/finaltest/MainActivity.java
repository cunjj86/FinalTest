package com.tje.finaltest;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tje.finaltest.adapters.MainViewPagerAdapter;
import com.tje.finaltest.databinding.ActivityMainBinding;

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

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}

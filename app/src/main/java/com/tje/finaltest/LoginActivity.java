package com.tje.finaltest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tje.finaltest.databinding.ActivityLogiBinding;

public class LoginActivity extends BaseActivity {

    ActivityLogiBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_logi);
    }
}
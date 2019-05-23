package com.tje.finaltest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tje.finaltest.databinding.ActivityNoticeBinding;
import com.tje.finaltest.datas.Notice;

public class NoticeActivity extends BaseActivity {

    ActivityNoticeBinding act;

    Notice notice;

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

        notice = (Notice) getIntent().getSerializableExtra("clickNotice");
        String dateFormat = notice.inputDtm.substring(0, 10);

        act.titleTxt.setText(notice.title);
        act.inputDateTxt.setText(dateFormat);
        act.contentTxt.setText(notice.content);

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_notice);

    }
}

package com.tje.finaltest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tje.finaltest.R;
import com.tje.finaltest.datas.Notice;

import java.util.List;

public class NoticeAdapter extends ArrayAdapter<Notice> {

    Context mContext;
    List<Notice> mList;
    LayoutInflater inf;

    public NoticeAdapter(Context context, List<Notice> list) {
        super(context, R.layout.notice_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.notice_list_item, null);
        }

        Notice noticeData = mList.get(position);

        TextView title = row.findViewById(R.id.titleTxt);
        TextView inputDate = row.findViewById(R.id.inputDateTxt);

        title.setText(noticeData.title);

        String inputDtm = noticeData.inputDtm;
        String resInputDate = inputDtm.substring(0, 10);

        inputDate.setText(resInputDate);

        return row;

    }
}

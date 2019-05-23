package com.tje.finaltest.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tje.finaltest.NoticeActivity;
import com.tje.finaltest.R;
import com.tje.finaltest.adapters.NoticeAdapter;
import com.tje.finaltest.databinding.FragmentNoticeBinding;
import com.tje.finaltest.datas.Notice;
import com.tje.finaltest.utils.ConnectServer;
import com.tje.finaltest.utils.ContextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends Fragment {

    FragmentNoticeBinding binding;

    List<Notice> noticeList = new ArrayList<>();
    NoticeAdapter noticeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false);

        noticeAdapter = new NoticeAdapter(getActivity(), noticeList);
        binding.noticeListView.setAdapter(noticeAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Notice clickedNoticeitem = noticeList.get(position);

                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                intent.putExtra("clickNotice", clickedNoticeitem);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        String token = ContextUtil.getUserToken(getActivity());

        ConnectServer.getRequestNoitice(getActivity(), token, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int code = 0;

                        try {
                            code = json.getInt("code");

                            if (code == 200) {

                                JSONObject data = json.getJSONObject("data");
                                JSONArray noticeArr = data.getJSONArray("announcements");

                                noticeList.clear();

                                for (int i=0; i < noticeArr.length(); i++) {

                                    JSONObject notice = noticeArr.getJSONObject(i);

                                    Notice noticeObj = Notice.getNoticeFromJson(notice);

                                    noticeList.add(noticeObj);

                                }

                                noticeAdapter.notifyDataSetChanged();

                            }
                            else {

                                try {
                                    String message = json.getString("message");
                                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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

}

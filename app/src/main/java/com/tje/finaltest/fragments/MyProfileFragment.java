package com.tje.finaltest.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tje.finaltest.R;
import com.tje.finaltest.databinding.FragmentMyProfileBinding;
import com.tje.finaltest.datas.MyProfile;
import com.tje.finaltest.utils.ConnectServer;
import com.tje.finaltest.utils.ContextUtil;

public class MyProfileFragment extends Fragment {

    FragmentMyProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setContent(MyProfile myProfile) {

        Glide.with(this).load(myProfile.logo).into(binding.profileImg);

        binding.userNameTxt.setText(myProfile.name);
        binding.billingTxt.setText(String.format("%s : %s", myProfile.bankName, myProfile.billingAcc));
        binding.phoneNumberTxt.setText(myProfile.phoneNumber);
        binding.emailTxt.setText(myProfile.email);
    }
}

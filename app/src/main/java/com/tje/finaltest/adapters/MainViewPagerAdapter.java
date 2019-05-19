package com.tje.finaltest.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tje.finaltest.fragments.MyProfileFragment;
import com.tje.finaltest.fragments.NoticeFragment;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    MyProfileFragment myProfileFrag = null;
    NoticeFragment noticeFrag = null;

    public MainViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fr = null;

        if (position == 0) {
            if (myProfileFrag == null) {
                myProfileFrag = new MyProfileFragment();
            }

            fr = myProfileFrag;
        }
        else if (position == 1) {
            if (noticeFrag == null) {
                noticeFrag = new NoticeFragment();
            }
            fr = noticeFrag;
        }

        return fr;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

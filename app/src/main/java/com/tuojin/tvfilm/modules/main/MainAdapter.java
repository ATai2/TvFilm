package com.tuojin.tvfilm.modules.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tuojin.tvfilm.base.BaseFragment;

import java.util.List;

/**
 * 文 件 名: MainAdapter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 17:09
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class MainAdapter extends FragmentPagerAdapter {

    List<BaseFragment> mList;

    public MainAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment=null;
        if (mList.size() > position) {
            fragment=mList.get(position);
            if (fragment != null) {
                return fragment;
            }
        }
        while (position > mList.size()) {
            mList.add(null);
        }
        fragment = mList.get(position);
        mList.set(position, fragment);

        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}

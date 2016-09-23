package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.bean.CategoryInfo;
import com.tuojin.tvfilm.contract.CategoryContract;

import java.util.ArrayList;
import java.util.List;

/**
* Created by MVPHelper on 2016/09/21
*/

public class CategoryModelImpl implements CategoryContract.Model{
    List<CategoryInfo> mList;
    @Override
    public List<CategoryInfo> initCategory() {
//        List<String> stringList=new ArrayList<>();
        mList=new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            CategoryInfo bean=new CategoryInfo();
//            stringList.add(i+1+"");
            bean.setTitle(i+"");
            mList.add(bean);
        }
        return mList;
    }
}
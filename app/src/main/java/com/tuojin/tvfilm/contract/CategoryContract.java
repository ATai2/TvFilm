package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.bean.CategoryInfo;

import java.util.List;

/**
 * 文 件 名: CategoryContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 13:50
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CategoryContract {
    public interface View {
        void setRecycleCategoryList(final List<CategoryInfo> datas);
    }

    public interface Presenter {
        void onResume();

    }

    public interface Model {
        List<CategoryInfo> initCategory();
    }


}
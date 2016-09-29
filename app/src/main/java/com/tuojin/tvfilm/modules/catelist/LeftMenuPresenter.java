package com.tuojin.tvfilm.modules.catelist;

import android.view.ViewGroup;
import android.widget.RadioButton;

import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;

import java.util.List;

/**
 * 文 件 名: LeftMenuPresenter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/26 17:19
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LeftMenuPresenter<T> extends OpenPresenter {

    List<T> mList;

    public LeftMenuPresenter(List<T> list) {
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RadioButton btn=new RadioButton(parent.getContext());
        return new ViewHolder(btn);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        RadioButton btn = (RadioButton) viewHolder.view;
        btn.setFocusableInTouchMode(true);
        btn.setFocusable(true);
        T t=mList.get(position);
        String str="";
        //补充其他类型
        if (t instanceof DirectListBean.DataBean.DirectorBean) {
            str=((DirectListBean.DataBean.DirectorBean) t).getMovie_director();
        } else if (t instanceof FilmTypeBean) {
            str=((FilmTypeBean) t).getMovieType();
        }

//        String str = mList.get(position).getMovie_director();
        btn.setText(str);
    }
}

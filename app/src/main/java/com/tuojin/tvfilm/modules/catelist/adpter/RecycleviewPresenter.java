package com.tuojin.tvfilm.modules.catelist.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import java.util.List;

/**
 * 文 件 名: RecycleviewPresenter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/30 12:49
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RecycleviewPresenter extends OpenPresenter {
    List<DirectorBean> mList;
    Context mContext;
    public RecycleviewPresenter(Context context,List<DirectorBean> list) {
        mList = list;
        mContext=context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_view, parent, false);
        return new GridViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GridViewHolder gridViewHolder = (GridViewHolder) viewHolder;
        DirectorBean directorBean = mList.get(position);
        String img = directorBean.getImg().replace("\\\\", "/");
        gridViewHolder.tv.setText(directorBean.getMovie_director());
        ImageLoaderUtils.showPictureWithApplicationWithNoMID(mContext,img,gridViewHolder.iv);
    }
}

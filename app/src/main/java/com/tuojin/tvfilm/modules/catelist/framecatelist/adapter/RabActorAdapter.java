package com.tuojin.tvfilm.modules.catelist.framecatelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.modules.catelist.framecatelist.CateFilmListActivty;
import com.tuojin.tvfilm.presenter.CateListFilmPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: RabActorAdapter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/29 11:43
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RabActorAdapter extends BaseAdapter {

    List<DirectListBean.DataBean.DirectorBean> mList;
    Context mContext;
    CateListFilmPresenterImpl mPresenter;

    public RabActorAdapter(List<DirectListBean.DataBean.DirectorBean> list, Context context, CateListFilmPresenterImpl presenter) {
        mList = list;
        mContext = context;
        mPresenter = presenter;
    }

    public RabActorAdapter(List<DirectListBean.DataBean.DirectorBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RabDirectorAdapter.DBViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_radbtn, parent, false);
            holder=new RabDirectorAdapter.DBViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder= (RabDirectorAdapter.DBViewHolder) convertView.getTag();
        holder.mRadbtnItem.setText(mList.get(position).getMovie_director());
        holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "onclick-listview", Toast.LENGTH_SHORT).show();
                mPresenter.onItemClick((CateFilmListActivty) mContext,mList.get(position));
            }
        });

        return convertView;
    }

    static class DBViewHolder {
        @BindView(R.id.radbtn_item)
        RadioButton mRadbtnItem;

        DBViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

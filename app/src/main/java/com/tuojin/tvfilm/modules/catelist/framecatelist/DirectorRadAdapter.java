package com.tuojin.tvfilm.modules.catelist.framecatelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.DirectorBean;

import java.util.List;

/**
 * 文 件 名: DirectorRadAdapter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/28 18:31
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectorRadAdapter extends RecyclerView.Adapter<DirectorRadAdapter.RadioBtnViewHolder> {
    List<DirectorBean> mList;
    Context mContext;
    OnItemClickListener mListener;


    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(DirectorBean bean);
    }

    public DirectorRadAdapter(Context context, List list) {
        mList = list;
        mContext = context;
    }

    @Override
    public RadioBtnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_radbtn, parent, false);
        RadioBtnViewHolder holder = new RadioBtnViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RadioBtnViewHolder holder, int position) {
        final DirectorBean bean = mList.get(position);
        holder.mBtn.setText(bean.getMovie_director());
        holder.mBtn.setTag(bean);
        final int flag = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class RadioBtnViewHolder extends RecyclerView.ViewHolder {
        RadioButton mBtn;

        RadioBtnViewHolder(View view) {
            super(view);
            mBtn = (RadioButton) view.findViewById(R.id.radbtn_item);
        }
    }
}

package com.tuojin.tvfilm.modules.main.hotrecomm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: RecommAdapter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 10:10
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RecommAdapter extends RecyclerView.Adapter<RecommAdapter.RecommViewHolder> {
    Context mContext;
    List<FilmBean> mList;
    OnItemClickListener mListener=null;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    //    List<>
    interface  OnItemClickListener{
        void onItemClick(FilmBean bean);
    }

    public RecommAdapter(Context context, List<FilmBean> list) {
        mContext = context;
        mList = list;
        int i = 0;
    }


    @Override
    public RecommViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recomm, parent, false);
        RecommViewHolder holder = new RecommViewHolder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.onItemClick();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommViewHolder holder, int position) {
        final FilmBean filmBean = mList.get(position);
        ImageLoaderUtils.showRecommIcom(mContext, filmBean.getPoster(), holder.mIvRecomm);
        holder.mTvRecomm.setText(filmBean.getMovie_name());
//        holder.mIvRecomm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(mContext, FilmDetailActivity.class);
////                intent.putExtra("film", filmBean);
////                mContext.startActivity(intent);
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(filmBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class RecommViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recomm)
        ImageView mIvRecomm;
        @BindView(R.id.tv_recomm)
        TextView mTvRecomm;

        RecommViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ImageLoaderUtils.setAnimation(mContext, itemView);
//            itemView.setOnClickListener();
        }
    }
}

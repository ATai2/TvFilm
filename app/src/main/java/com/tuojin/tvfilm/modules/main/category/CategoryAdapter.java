package com.tuojin.tvfilm.modules.main.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.CategoryInfo;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import java.util.List;

/**
 * 文 件 名: CategoryAdapter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 14:00
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    public static final int LONG = 0;//长类型
    public static final int SMALL = 1;//短类型

    Context mContext;
    List<CategoryInfo> mList;
    OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(int bean);
    }

    public CategoryAdapter(Context context, List<CategoryInfo> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final CategoryViewHolder mCategoryViewHolder;
//        view = LayoutInflater.from(mContext).inflate(R.layout.item_category_long, parent, false);

        if (viewType == LONG) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category_long, parent, false);
            mCategoryViewHolder = new CategoryViewHolder(view);
            return mCategoryViewHolder;
        }
        if (viewType == SMALL) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category_small, parent, false);
            mCategoryViewHolder = new CategoryViewHolder(view);
            return mCategoryViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        //title
        holder.mTVCategoryLong.setText(mList.get(position).getTitle());
        ImageLoaderUtils.setAttributeAnimation(mContext, holder.itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);
            }
        });
        //pic
//        holder.mIVCategoryLong.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    //设置控件类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2) {
            return LONG;
        } else {
            return SMALL;
        }
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView mIVCategoryLong;
        TextView mTVCategoryLong;

        CategoryViewHolder(View view) {
            super(view);
            mIVCategoryLong = (ImageView) view.findViewById(R.id.mIVCategory);
            mTVCategoryLong = (TextView) view.findViewById(R.id.mTVCategory);
            ImageLoaderUtils.setAnimation(mContext, itemView);
        }
    }
}

package com.tuojin.tvfilm.modules.main.category;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.CategoryInfo;

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

    public CategoryAdapter(Context context, List<CategoryInfo> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        CategoryViewHolder mCategoryViewHolder;
        if (viewType == LONG) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category_long, parent, false);
        }
        if (viewType == SMALL) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category_small, parent, false);
        }
        mCategoryViewHolder = new CategoryViewHolder(view);
        return mCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        //title
        holder.mTVCategoryLong.setText(mList.get(position).getTitle());
        //pic
//        holder.mIVCategoryLong.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //设置控件类型
    @Override
    public int getItemViewType(int position) {
        if (position == 1) {
            return LONG;
        } else {
            return SMALL;
        }
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.mIVCategoryLong)
        ImageView mIVCategoryLong;
//        @BindView(R.id.mTVCategoryLong)
        TextView mTVCategoryLong;
//        @BindView(R.id.item_category_long)
        CardView mItemCategoryLong;

        CategoryViewHolder(View view) {
            super(view);
//            ButterKnife.bind(this, view);
            mIVCategoryLong = (ImageView) view.findViewById(R.id.mIVCategory);
            mTVCategoryLong = (TextView) view.findViewById(R.id.mTVCategory);
            mItemCategoryLong= (CardView) view.findViewById(R.id.item_category);
        }
    }
}

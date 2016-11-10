package com.tuojin.tvfilm.modules.main.sortlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

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
public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> {
    public static final int LONG = 0;//长类型
    public static final int SMALL = 1;//短类型
    private final int[] mIntArray;
    private final String[] mString;

    Context mContext;
    //  List<String> mList;
    OnItemClickListener mListener;


    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(int bean);
    }

    public SortAdapter(Context context) {
        mContext = context;

        mIntArray = new int[]{R.mipmap.sort_new,
                R.mipmap.sort_hot,
                R.mipmap.sort_big,
                R.mipmap.sort_ad,
                R.mipmap.sort_douban,};
        mString =new String[]{
                "类型",
                "年份",
                "地区",
                "直播",
                "导演",
                "演员"
        };
    }

    @Override
    public SortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final SortViewHolder mCategoryViewHolder;
//        view = LayoutInflater.from(mContext).inflate(R.layout.item_category_long, parent, false);

        if (viewType == LONG) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category_long, parent, false);
            mCategoryViewHolder = new SortViewHolder(view);
            return mCategoryViewHolder;
        }
        if (viewType == SMALL) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category_small, parent, false);
            mCategoryViewHolder = new SortViewHolder(view);
            return mCategoryViewHolder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(SortViewHolder holder, final int position) {
        //title
        //  holder.mTVCategoryLong.setText(mList.get(position).getTitle());
        holder.mIVCategoryLong.setImageResource(mIntArray[position]);
        ImageLoaderUtils.setAnimation(mContext, holder.itemView);
        holder.mTVCategoryLong.setText(mString[position]);

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
        return mIntArray.length;
    }

    //设置控件类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 1 || position == 2) {
            return LONG;
        } else {
            return SMALL;
        }
    }


    public class SortViewHolder extends RecyclerView.ViewHolder {
        ImageView mIVCategoryLong;
        TextView mTVCategoryLong;

        SortViewHolder(View view) {
            super(view);
            mIVCategoryLong = (ImageView) view.findViewById(R.id.mIVCategory);
            mTVCategoryLong = (TextView) view.findViewById(R.id.mTVCategory);
//            ImageLoaderUtils.setAnimation(mContext, itemView);
        }
    }
}

package com.tuojin.tvfilm.modules.catelist.framecatelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: DirectorListAdapter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/30 14:34
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectorListAdapter  extends RecyclerView.Adapter<DirectorListAdapter.DirectorListViewHoler>{

    private Context mContext;
    public static final int PAGE_SIZE = 8; // 每一屏幕显示8 Button
    private List<DirectorBean> mDatas=new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;

    @Override
    public DirectorListViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_app,parent,false);
        return new DirectorListViewHoler(view);
    }

    @Override
    public void onBindViewHolder(final DirectorListViewHoler holder, final int position) {
        DirectorBean bean=mDatas.get(position);
        ImageLoaderUtils.showPictureWithApplicationWithNoMID(mContext,bean.getImg(),holder.pic);
        holder.title.setText(bean.getMovie_director());
//        ImageLoaderUtils.setAttributeAnimation(mContext,holder.itemView);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, mDatas.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, DirectorBean data);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public DirectorListAdapter(Context context, List<DirectorBean> datas,int page) {
        mContext = context;
        int i=page*PAGE_SIZE;
        int end=i+PAGE_SIZE;
        while ((i < datas.size()) && (i < end)) {
            mDatas.add(datas.get(i));
            i++;
        }
        mInflater = LayoutInflater.from(mContext);
    }


    public class DirectorListViewHoler extends RecyclerView.ViewHolder {
        public ImageView pic;
        public TextView title;

        public DirectorListViewHoler(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.app_image);
            title = (TextView) itemView.findViewById(R.id.app_title);

        }
    }

}

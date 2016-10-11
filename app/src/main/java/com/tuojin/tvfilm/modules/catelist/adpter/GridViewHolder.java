package com.tuojin.tvfilm.modules.catelist.adpter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.tuojin.tvfilm.R;


public class GridViewHolder extends OpenPresenter.ViewHolder {

    public ImageView iv;
    public TextView tv;
    public TextView head_tv;

    public GridViewHolder(View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.grid_view_item_test);
        tv = (TextView) itemView.findViewById(R.id.textView);
    }

}

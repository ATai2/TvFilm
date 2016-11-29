package com.tuojin.tvfilm.modules.catelist.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tuojin.tvfilm.utils.ImageLoaderUtils;

/**
 * 对Recycleview的封装类。
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//稀疏数组维护view列表
    private int mPosition;
    private View mConvertView;
    private Context mContext;
    private int mLayoutId;

    public ViewHolder(Context context, View itemView, ViewGroup parent, int position) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mPosition = position;
        mViews = new SparseArray<>();
        mConvertView.setTag(this);

//        mConvertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    v.setBackgroundResource(R.drawable.border_two);
//                } else {
//                    v.setBackground(null);
//                }
//            }
//        });
    }


    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            ViewHolder holder = new ViewHolder(context, itemView, parent, position);
            holder.mLayoutId = layoutId;
            return holder;
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }


    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setRadioButtonText(int viewId, String text) {
        RadioButton tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, String resId) {
        ImageView view = getView(viewId);
        ImageLoaderUtils.showPictureWithApplication(mContext, resId, view);
        return this;
    }

    public ViewHolder setImageResourceNOEnlarge(int viewId, String resId) {
        ImageView view = getView(viewId);
//        ImageLoaderUtils.showPictureWithApplication(mContext, resId, view);
        return this;
    }

    public ViewHolder setImageResourceNoMID(int viewId, String resId) {
        ImageView view = getView(viewId);
        ImageLoaderUtils.showPictureWithApplicationWithNoMID(mContext, resId, view);
        return this;
    }

    public ViewHolder setSearchImageResource(int viewId, String resId) {
        ImageView view = getView(viewId);
        //  ImageLoaderUtils.showPictureWithApplication(mContext, resId, view);
        ImageLoaderUtils.showPictureWithSearch(mContext, resId, view);
        return this;
    }

    public ViewHolder setScaleAnimation(int viewId) {
        TextView textView = getView(viewId);
        ImageLoaderUtils.setScaleAnimation(mContext, itemView, textView);
        return this;
    }

    public ViewHolder setPropertyAnimation(int viewId) {
        TextView textView = getView(viewId);
        ImageLoaderUtils.setAttributeAnimation(mContext, itemView);
        return this;
    }

    public ViewHolder setNoTextAnimation() {
        // ImageView imageView = getView(viewId);
        ImageLoaderUtils.setNoTextAnimation(mContext, itemView);
        return this;
    }


    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }
//    interface FocusChangeListener{
//        void focusChange();
//    }
//    public ViewHolder setFocusChangeListener(int viewId) {
//        ImageView view = getView(viewId);
//        view.setImageBitmap(bitmap);
//        return this;
//    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }


    public ViewHolder setOnTouchListener(int viewId,
                                         View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public void updatePosition(int position) {
        mPosition = position;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    public void setOnTextFocusChangeListner(int group, final int iId, final int ivId, View.OnFocusChangeListener onFocusChangeListener) {
        View view = getView(group);

        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            View t = getView(iId);
            View p = getView(ivId);
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    v.startAnimation(AnimationUtils.loadAnimation(mContext,
//                            R.anim.scale_out));
                    t.setVisibility(View.VISIBLE);
//                    p.setBackgroundResource(R.drawable.btn_selector);
                } else {
//                    v.startAnimation(AnimationUtils.loadAnimation(mContext,
//                            R.anim.scale_in));
                    t.setVisibility(View.INVISIBLE);
//                    p.setBackground(null);
                }
            }
        });
    }
}

package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.modules.catelist.framecatelist.CateFilmListActivty;

import java.util.List;

/**
 * 文 件 名: CateListFilmContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 12:58
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CateListFilmContract {
public interface View extends BaseView{
    void initViewRadioGroup(List<DirectListBean.DataBean.DirectorBean> list);

    void initFilmFragment(List<FilmBean> data1);
}

public interface Presenter{
    void initRadioGroup(int position);

    void initViewRadioGroup(List<DirectListBean.DataBean.DirectorBean> list);

    void onItemClick(CateFilmListActivty cateFilmListActivty, DirectListBean.DataBean.DirectorBean directListBean);

    void initFilmFragment(List<FilmBean> data1);

    void onFilmTypeItemClick(CateFilmListActivty context, FilmTypeBean filmTypeBean);
}

public interface Model{
    void initRadioGroup(int position);

    void onItemClick(DirectListBean.DataBean.DirectorBean directBean);

    void onFilmTypeItemClick(FilmTypeBean filmTypeBean);
}


}
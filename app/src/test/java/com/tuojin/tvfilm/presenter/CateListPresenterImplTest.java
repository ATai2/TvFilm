package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.bean.DirectorBean;

import org.junit.Test;

/**
 * 文 件 名: CateListPresenterImplTest
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 10:11
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CateListPresenterImplTest {
    @Test
    public void initRadioButton() throws Exception {

    }

    @Test
    public void initViewRadioButton() throws Exception {

    }
    @Test
    public  void testDirector(){
        CateListPresenterImpl<DirectorBean> presenter=new CateListPresenterImpl<>();
        presenter.initRadioButton(0);
        int i=1;
    }

}
package com.tuojin.myapplication;


import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: RxTest
 * 创 建 人: Administrator
 * 创建日期: 2016/11/25 18:38
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RxTest {
    @Test
    public void TestObser(){
        Observable.just("a", "b", "c", "d").observeOn(Schedulers.computation())
        .map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                System.out.println("name:"+Thread.currentThread().getName()+":first-----");

                return s+s;
            }
        }).observeOn(Schedulers.io())
        .map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                System.out.println("name:"+Thread.currentThread().getName()+":first-----");

                return s+s;
            }
        }).observeOn(Schedulers.newThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.print(Thread.currentThread().getName());
                System.out.print("completed");
            }
        });
    }
}

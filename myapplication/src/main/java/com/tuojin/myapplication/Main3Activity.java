package com.tuojin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Observable.just("a", "b", "c", "d").observeOn(Schedulers.computation())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d("Main3Activity", ("name:" + Thread.currentThread().getName() + ":first-----"));

                        return s + s;
                    }
                }).observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d("Main3Activity", ("name:" + Thread.currentThread().getName() + ":first-----"));

                        return s + s;
                    }
                }).observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("Main3Activity", (Thread.currentThread().getName()));
                        Log.d("Main3Activity", ("completed"));
                    }
                });
    }
}

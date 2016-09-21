//package com.tuojin.tvfilm.net;
//
//import android.content.Context;
//
//import cn.finalteam.okhttpfinal.HttpRequest;
//import cn.finalteam.okhttpfinal.RequestParams;
//import okhttp3.Headers;
//import okhttp3.Response;
//
///**
// * 文 件 名: TvFilmNetWork
// * 创 建 人: Administrator
// * 创建日期: 2016/9/19 09:26
// * 文件描述： 网络请求类，所有的网络请求都是通过Okhttpfinal框架进行。框架缓存机制
// *                  ----参考百事通项目网络请求封装
// * 邮   箱:
// * 博   客:
// * 修改时间：
// * 修改备注：
// */
//public class TvFilmNetWork {
//    public static final String TAG="TvFilmNetWork";
//    private Context mContext;
//
//    public TvFilmNetWork(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    public void post(String url, RequestParams params,final Success success,final Failure failure){
//        HttpRequest.post(url,params,new MyBaseHttpRequestCallback(){
//            @Override
//            protected void onSuccess(BaseApiResponse baseApiResponse) {
//                int code=baseApiResponse.getStatus();
//                if (code==3){
//                    success.execute(baseApiResponse.getData());
//                }else {
//                    failure.execute(code,baseApiResponse.getData().toString());
//                }
//            }
//
//            @Override
//            public void onResponse(Response httpResponse, String response, Headers headers) {
//                super.onResponse(httpResponse, response, headers);
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//
//            }
//        });
//    }
//
//
//                //    处理成功或失败的接口
//        public interface Success {
//            void execute(Object object);
//        }
//
//        public interface Failure {
//            void execute(int code,String message);
//        }
//
//}

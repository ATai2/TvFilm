package com.tuojin.tvfilm.net;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.tuojin.tvfilm.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 文 件 名: TvFilmNetWorkWS
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 15:21
 * 文件描述：应用开启即启动socket，应用关闭，关闭。
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class TvFilmNetWorkWS {

    public void sendMsg(final String cmd, final Success success, final Failure failure) {
        AsyncHttpClient.getDefaultInstance().websocket("ws://192.168.1.243:8081/bestv-cinema-player/message?mac=PAD1465889962927"
                , null, new AsyncHttpClient.WebSocketConnectCallback() {
                    @Override
                    public void onCompleted(Exception ex, WebSocket webSocket) {
                        if (ex != null) {
                            ex.printStackTrace();
//                            LogUtils.d();
                            return;
                        }
                        webSocket.send(cmd);
                        webSocket.setStringCallback(new WebSocket.StringCallback() {
                            public void onStringAvailable(String s) {
                                LogUtils.d("ws", s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    int status = jsonObject.getInt("status");
                                    if (status == 3) {
                                        success.excute(s);
                                    } else {
                                        failure.excute(s);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }
                });
    }

    public interface Success {
        void excute(String data);
    }

    public interface Failure {
        void excute(String data);
    }
}

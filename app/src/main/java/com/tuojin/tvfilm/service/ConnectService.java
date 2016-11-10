package com.tuojin.tvfilm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.tuojin.tvfilm.event.CallBack;
import com.tuojin.tvfilm.event.CmdEvent;
import com.tuojin.tvfilm.net.socket.WebSocket;
import com.tuojin.tvfilm.net.socket.WebSocketCall;
import com.tuojin.tvfilm.net.socket.WebSocketListener;
import com.tuojin.tvfilm.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class ConnectService extends Service {
    String TAG = "CS";
    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    public WebSocketCall mWebSocketCall;
    ConnectionBinder mConnectionBinder;
    private WebSocket mWebSocket;
    private StringCallBack mStringCallBack;
    String msg;

    public void setStringCallBack(StringCallBack stringCallBack) {
        mStringCallBack = stringCallBack;
    }

    public interface StringCallBack {
        void excute(String str);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus.getDefault().register(this);
        mConnectionBinder = new ConnectionBinder();
        mOkHttpClient = new OkHttpClient.Builder().readTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .build();

        mRequest = new Request.Builder()
                .url(Constant.WSSITE)
                .build();
        mWebSocketCall = WebSocketCall.create(mOkHttpClient, mRequest);
        mWebSocketCall.enqueue(new WebSocketListener() {

            @Override
            public void onOpen(com.tuojin.tvfilm.net.socket.WebSocket webSocket, Response response) {
                Log.d(TAG, "onOpen");
                mWebSocket = webSocket;

//
//                sSendMSG("PAD1465889962927|login|username=admin&pwd=123456", mStringCallBack);
//                sSendMSG("PAD1465889962927|getTerminal|mac=PAD1465889962927", mStringCallBack);
            }

            @Override
            public void onFailure(IOException e, Response response) {
                Log.d(TAG, "onFailure: ");
            }

            /**
             * 接收到消息
             * @param message
             * @throws IOException
             */
            @Override
            public void onMessage(ResponseBody message) throws IOException {

                String string = message.string();
                Log.d(TAG, "onMessage" + string);

//                sendText(message.string());
//                mStringCallBack.excute(message.string());
                EventBus.getDefault().post(new CallBack(string));
                message.source().close();
            }

            @Override
            public void onPong(Buffer payload) {
                Log.d(TAG, "onPong: ");
            }

            @Override
            public void onClose(int code, String reason) {
                Log.d(TAG, "onClose: " + reason);
            }
        });

    }

    private String sendText(String string) {

        if (mStringCallBack != null) {
//            mStringCallBack.excute(string);
        }
        return string;
    }

    public ConnectService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mConnectionBinder;
    }

    public class ConnectionBinder extends Binder {
        public ConnectService getServiceInstance() {
            return ConnectService.this;
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(CmdEvent event) {
        String cmd=event.msg;
        Log.d(TAG, "onMessage" + cmd);
//        mStringCallBack = stringCallBack;
        RequestBody requestBody = RequestBody.create(WebSocket.TEXT, cmd);
        if (mWebSocket != null) {
            try {
                mWebSocket.sendMessage(requestBody);
//                requestBody
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sSendMSG(String cmd, StringCallBack stringCallBack) {
        Log.d(TAG, "onMessage" + cmd);
        mStringCallBack = stringCallBack;
        RequestBody requestBody = RequestBody.create(WebSocket.TEXT, cmd);
        if (mWebSocket != null) {
            try {
                mWebSocket.sendMessage(requestBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

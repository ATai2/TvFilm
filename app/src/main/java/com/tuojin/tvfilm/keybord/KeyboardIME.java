//package com.tuojin.tvfilm.keybord;
//
//import android.app.Activity;
//import android.content.Context;
//import android.inputmethodservice.InputMethodService;
//import android.inputmethodservice.Keyboard;
//import android.support.v7.widget.RecyclerView;
//import android.widget.EditText;
//import com.tuojin.tvfilm.R;
//import java.util.List;
//
//
///**
// * Created by WX on 3/30 0030.
// */
//public class KeyboardIME extends InputMethodService{
//    private Context ctx;
//    private Activity act;
//    private SimpleKeyBoardView keyboardView;
//    private Keyboard k1;// 字母键盘
//    private Keyboard k2;// 数字键盘
//    private EditText ed;
//    private int nCurKeyboardKeyNums;
//    private List<Keyboard.Key> nKeys;
//    public KeyboardIME(Activity act, Context ctx, EditText edit, RecyclerView mRecyclerView, RecyclerView two_recyclerview) {
//        this.act = act;
//        this.ctx = ctx;
//        this.ed = edit;
//        k1 = new Keyboard(ctx, R.xml.qwerty);
//        k2 = new Keyboard(ctx, R.xml.symbols);
//        keyboardView = (SimpleKeyBoardView) act.findViewById(R.id.keyboard_view);
//        keyboardView.setKeyboard(k1);
//        nKeys = k1.getKeys();
//        nCurKeyboardKeyNums = nKeys.size();
//        keyboardView.requestFocus();
//        keyboardView.setEnabled(true);
//        keyboardView.setPreviewEnabled(true);
//        SimpleKeyBoardView.keyboardView=keyboardView;
//        SimpleKeyBoardView.editText=ed;
//        SimpleKeyBoardView.qwertyKeyboard=k1;
//        SimpleKeyBoardView.symbolsKeyboard=k2;
//        SimpleKeyBoardView.mRecyclerView=mRecyclerView;
//        SimpleKeyBoardView.two_recyclerview=two_recyclerview;
//    }
//}

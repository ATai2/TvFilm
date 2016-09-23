package com.tuojin.tvfilm.keybord;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wx on 3/31 0031.
 */
public class SimpleKeyBoardView extends KeyboardView {

    public static Keyboard qwertyKeyboard;  //字母键盘
    public static Keyboard symbolsKeyboard; //数字键盘
    private List<Keyboard.Key> qwertyKeys = new ArrayList<>();  //字母键盘的集合
    private Keyboard.Key focusedKey;        //焦点KEY
    private Rect rect;
    public static RecyclerView mRecyclerView,two_recyclerview;

    public static SimpleKeyBoardView keyboardView;  //键盘视图

    private int nCurKeyboardKeyNums;                //键子总数

    private int nLastKeyIndex = 0;                 //当前焦点在哪一个键子上
    public boolean isnun = false;                  // 是否数据键盘

    public static EditText editText;
    public static  Editable editable;
    private int start;
    private Context context;

    private OnLitener mOnLitener;
    public  static boolean isOnDraw;
    public SimpleKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
    public interface OnLitener
    {
        void onClick(Editable editable);
    }
    public void setOnLitener(OnLitener mOnLitener)
    {
        this.mOnLitener = mOnLitener;
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //判断当前是数字键盘时 定义集合List
        if (isnun) {
            qwertyKeys = symbolsKeyboard.getKeys();
        } else {
            qwertyKeys = qwertyKeyboard.getKeys();
        }
        Paint p = new Paint();
        if (isOnDraw){
            p.setAlpha(0);
        }else{
            p.setColor(Color.parseColor("#2a5399"));
        }
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3.75f);
        focusedKey = qwertyKeys.get(nLastKeyIndex);
        rect = new Rect(
                focusedKey.x, focusedKey.y + 4,
                focusedKey.x + focusedKey.width,
                focusedKey.y + focusedKey.height
        );
        canvas.drawRect(rect, p);

    }



    public int getLastKeyIndex() {
        return nLastKeyIndex;
    }

    public void setLastKeyIndex(int index) {
        this.nLastKeyIndex = index;
    }

    private void setFields() {
        if (null == keyboardView) return;
        //判断当前是数字键盘时 定义集合List
        if (isnun) {
            qwertyKeys = symbolsKeyboard.getKeys();
        } else {
            qwertyKeys = qwertyKeyboard.getKeys();
        }
        nCurKeyboardKeyNums = qwertyKeys.size();
        nLastKeyIndex = keyboardView.getLastKeyIndex();

        editable = editText.getText();
        start = editText.getSelectionStart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (event.getRepeatCount() == 0 && keyboardView != null) {
                    if (keyboardView.handleBack()) {
                        return true;
                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                setFields();
                Keyboard mKeyboard = null;
                if (isnun) {
                    mKeyboard = symbolsKeyboard;
                } else {
                    mKeyboard = qwertyKeyboard;
                }
                if (nLastKeyIndex >= nCurKeyboardKeyNums - 1) {
                    if (null == keyboardView) return false;
                    keyboardView.setLastKeyIndex(0);
                } else {
                    int[] nearestKeyIndices = mKeyboard.getNearestKeys(
                            qwertyKeys.get(nLastKeyIndex).x, qwertyKeys.get(nLastKeyIndex).y);

                    for (int index : nearestKeyIndices) {
                        if (nLastKeyIndex < index) {
                            Keyboard.Key nearKey = qwertyKeys.get(index);
                            Keyboard.Key lastKey = qwertyKeys.get(nLastKeyIndex);
                            if (
                                    ((lastKey.x >= nearKey.x) // left side compare
                                            && (lastKey.x < (nearKey.x + nearKey.width)))
                                            || (((lastKey.x + lastKey.width) > nearKey.x) // right side compare
                                            && ((lastKey.x + lastKey.width) <= (nearKey.x + nearKey.width)))) {
                                keyboardView.setLastKeyIndex(index);
                                break;
                            }
                        }
                    }
                }
                keyboardView.invalidate();
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                setFields();
                if (nLastKeyIndex <= 0) {

                    if (null == keyboardView) return false;

                    keyboardView.setLastKeyIndex(nCurKeyboardKeyNums - 1);

                } else {

                    int[] nearestKeyIndices = qwertyKeyboard.getNearestKeys(

                            qwertyKeys.get(nLastKeyIndex).x, qwertyKeys.get(nLastKeyIndex).y);

                    for (int i = nearestKeyIndices.length - 1; i >= 0; i--) {

                        int index = nearestKeyIndices[i];

                        if (nLastKeyIndex > index) {

                            Keyboard.Key nearKey = qwertyKeys.get(index);// get the next key

                            Keyboard.Key nextNearKey = qwertyKeys.get(index + 1);

                            Keyboard.Key lastKey = qwertyKeys.get(nLastKeyIndex);// get current displayed

                            if (

                                    ((lastKey.x >= nearKey.x) &&

                                            (lastKey.x < (nearKey.x + nearKey.width)) &&

                                            (((lastKey.x + lastKey.width) <= (nextNearKey.x + nextNearKey.width))

                                                    || ((lastKey.x + lastKey.width) > nextNearKey.x)))
                                    ) {
                                keyboardView.setLastKeyIndex(index);
                                break;
                            }
                        }
                    }
                }
                keyboardView.invalidate();
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                setFields();
                if (nLastKeyIndex <= 0) {
                    if (null == keyboardView) return false;
                    keyboardView.setLastKeyIndex(nCurKeyboardKeyNums - 1);
                } else {
                    nLastKeyIndex--;
                    keyboardView.setLastKeyIndex(nLastKeyIndex);
                }
                keyboardView.invalidate();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                setFields();
                if (nCurKeyboardKeyNums==28){
                    if (nLastKeyIndex==4||nLastKeyIndex==9||nLastKeyIndex==14
                    ||nLastKeyIndex==19||nLastKeyIndex==24||nLastKeyIndex==27){
                      if (mRecyclerView.getVisibility()==VISIBLE){
                          mRecyclerView.requestFocus();
                          isOnDraw=true;
                      }else if (two_recyclerview.getVisibility()==VISIBLE){
                          two_recyclerview.requestFocus();
                          isOnDraw=true;
                      }
                        keyboardView.setLastKeyIndex(-1);
                    }else {
                        if (mRecyclerView.getVisibility()==VISIBLE){
                            mRecyclerView.setFocusable(false);
                        }else if (two_recyclerview.getVisibility()==VISIBLE){
                            two_recyclerview.setFocusable(false);
                        }
                    }
                }
                else if (nCurKeyboardKeyNums==12){
                    if (nLastKeyIndex==2||nLastKeyIndex==5||nLastKeyIndex==8
                            ||nLastKeyIndex==11){
                        if (mRecyclerView.getVisibility()==VISIBLE){
                            mRecyclerView.requestFocus();
                            isOnDraw=true;
                        }else if (two_recyclerview.getVisibility()==VISIBLE){
                            two_recyclerview.requestFocus();
                            isOnDraw=true;
                        }
                    }else {
                        mRecyclerView.setFocusable(false);
                        if (mRecyclerView.getVisibility()==VISIBLE){
                            mRecyclerView.setFocusable(false);
                        }else if (two_recyclerview.getVisibility()==VISIBLE){
                            two_recyclerview.setFocusable(false);
                        }
                    }
                }

                if (nLastKeyIndex >= nCurKeyboardKeyNums - 1) {
                    if (null == keyboardView) return false;
                    keyboardView.setLastKeyIndex(0);
                } else {
                    nLastKeyIndex++;
                    keyboardView.setLastKeyIndex(nLastKeyIndex);
                }
                keyboardView.invalidate();
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                if (null == keyboardView) return false;
                setFields();
                int curKeyCode = qwertyKeys.get(nLastKeyIndex).codes[0];

                if (curKeyCode == Keyboard.KEYCODE_MODE_CHANGE) {
                    if (isnun) {
                        isnun = false;
                        keyboardView.setKeyboard(qwertyKeyboard);
                    } else {
                        isnun = true;
                        keyboardView.setKeyboard(symbolsKeyboard);
                    }
                    keyboardView.setLastKeyIndex(0);
                } else if (curKeyCode == Keyboard.KEYCODE_DELETE) {// 回退
                    if (editable != null && editable.length() > 0) {
                        if (start > 0) {
                            editable.delete(start - 1, start);
                            mOnLitener.onClick(editable);
                        }
                        if (start==0){
                            keyboardView.setLastKeyIndex(0);
                        }
                    }
                } else {
                    editable.insert(start, Character.toString((char) curKeyCode));
                    mOnLitener.onClick(editable);
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

package com.sonaive.expandwrapkeyboarddemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by liutao on 11/17/15.
 */
public class MainActivity extends AppCompatActivity {

    private SmoothShiftingLayout mScrollLayout;
    private ListView mListView;
    private View mEditTab;
    private boolean hasKeyboardShowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mScrollLayout = (SmoothShiftingLayout) findViewById(R.id.root);
        mListView = (ListView) findViewById(R.id.list_view);
        mEditTab = findViewById(R.id.edit_tab);
        mScrollLayout.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutChangeListener(mScrollLayout));
        ArrayList<String> data = new ArrayList<>();
        for( int i = 0; i < 30; i++) {
            data.add(String.valueOf(i));
        }
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    class GlobalLayoutChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

        private int screenHeight;
        private int keyboardHeight;
        private boolean isKeyboardVisible;
        private Rect rect;
        private View root;

        public GlobalLayoutChangeListener(View root) {
            this.root = root;
        }

        /**
         * the result is pixels
         */
        @Override
        public void onGlobalLayout() {

            rect = new Rect();
            root.getWindowVisibleDisplayFrame(rect);
            screenHeight = root.getRootView().getHeight();
            keyboardHeight = screenHeight - (rect.bottom - rect.top);
            Log.d("screenHeighGlobalLayout", "screenHeight:GlobalLayout " + keyboardHeight);
            Log.d("Keyboard Size", "Size: " + keyboardHeight);

            isKeyboardVisible = keyboardHeight > screenHeight / 3;
            if (isKeyboardVisible) {
                hasKeyboardShowed = true;
                mScrollLayout.setShiftingDistance(keyboardHeight);
                mScrollLayout.shift(SmoothShiftingLayout.DIRECTION_UP);
            } else if (hasKeyboardShowed) {
                hasKeyboardShowed = false;
                mScrollLayout.shift(SmoothShiftingLayout.DIRECTION_DOWN);
            }
        }
    }
}

package com.step2hell.newsmth.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;

import com.step2hell.newsmth.R;

public class SelectableEditText extends AppCompatEditText {

    private Drawable mDrawableRight;
    private ListPopupWindow mListPopupWindow;
    private ArrayAdapter mAdapter;

    private String[] mDataArr = new String[]{};


    public SelectableEditText(@NonNull Context context) {
        this(context, null);
    }

    public SelectableEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDrawableRight = context.getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp);
        mDrawableRight.setBounds(0, 0, 1 << 6, 1 << 6);
        setCompoundDrawables(null, null, mDrawableRight, null);
        initListPopupWindow();
    }

    private void initListPopupWindow() {
        mListPopupWindow = new ListPopupWindow(getContext());
        mListPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mListPopupWindow.setPromptPosition(ListPopupWindow.POSITION_PROMPT_BELOW);
        mListPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        mListPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        mListPopupWindow.setAnchorView(this);
        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mDataArr);
        mListPopupWindow.setAdapter(mAdapter);
        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setText(mDataArr[position]);
                setSelection(getText().length());
                mListPopupWindow.dismiss();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touchDropDownArrow = event.getRawX() >= (getX() + getWidth() - mDrawableRight.getBounds().width());
        if (!touchDropDownArrow) {
            return super.onTouchEvent(event);
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if ((mDrawableRight != null)
                            && (mListPopupWindow != null)
                            && !mListPopupWindow.isShowing()) {
                        hideSoftInput();
                        mListPopupWindow.show();
                        mListPopupWindow.getListView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
                    }
                    break;
            }
            return true;
        }
    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
    }

    public void setListData(String[] datas) {
        mDataArr = datas;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }
}

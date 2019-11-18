package com.example.customedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class MyEditText extends AppCompatEditText {
    Drawable dark , light;
    public  MyEditText(Context context , AttributeSet attributeSet){
        super(context , attributeSet);
        init();
    }

        void init()
        {
            light = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_clear_light_24dp,null);
            dark = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_clear_black_24dp,null);
            addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                   showButton();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event ) {
                    boolean isClicked = false;
                    float clearButtonStart = getWidth()- getPaddingEnd()-light.getIntrinsicWidth();
                    if (event.getX()>clearButtonStart)
                    {
                        isClicked = true;
                    }
                    if (isClicked)
                    {
                        switch (event.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:
                            getText().clear();
                            break;
                            case MotionEvent.ACTION_UP:
                                showDarkButton();
                                break;
                        }
                    }
                    return true;
                }
            });
        }

    private void showButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,light,null);
    }

    private void showDarkButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,dark,null);
    }


    public MyEditText(Context context, Drawable dark, Drawable light) {
        super(context);
        this.dark = dark;
        this.light = light;
    }

    public MyEditText(Context context, AttributeSet attrs, Drawable dark, Drawable light) {
        super(context, attrs);
        this.dark = dark;
        this.light = light;
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, Drawable dark, Drawable light) {
        super(context, attrs, defStyleAttr);
        this.dark = dark;
        this.light = light;
    }
}

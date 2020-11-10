package com.jaehyun.jhpaint;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomPaletteView extends LinearLayout {
    private final Context mContext;

    public CustomPaletteView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CustomPaletteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public CustomPaletteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        String infService = mContext.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        ConstraintLayout layout = (ConstraintLayout)li.inflate(R.layout.view_custom_palette, this, false);
        addView(layout);

    }
}

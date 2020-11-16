package com.jaehyun.jhpaint;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomPaletteView extends LinearLayout {
    private final Context mContext;
    private ColorRecyclerViewAdapter adapter;

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

    public void setOnPickListener(ColorRecyclerViewAdapter.OnPickColorListener listener){
        adapter.setOnPickListener(listener);
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        ConstraintLayout layout = (ConstraintLayout)li.inflate(R.layout.view_custom_palette, this, false);
        RecyclerView mRecyclerView = layout.findViewById(R.id.recyclerView);
        adapter = new ColorRecyclerViewAdapter(mContext);
        adapter.getList().add(Color.RED);
        adapter.getList().add(Color.WHITE);
        adapter.getList().add(Color.YELLOW);
        adapter.getList().add(Color.GRAY);

        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        addView(layout);
    }


}
